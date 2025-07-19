package br.com.chronus.gerenciamento.infrastructure.service;

import br.com.chronus.gerenciamento.application.domain.ContatoAnjo;
import br.com.chronus.gerenciamento.application.domain.Mensagem;
import br.com.chronus.gerenciamento.application.domain.Paciente;
import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import br.com.chronus.gerenciamento.application.service.MensagemService;
import br.com.chronus.gerenciamento.application.service.TratamentoSchedulerService;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.TratamentoEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TratamentoSchedulerServiceImpl implements TratamentoSchedulerService {

    private final TratamentoGateway tratamentoGateway;
    private final ConsultaGateway consultaGateway;
    private final MensagemService mensagemService;
    private final PacienteGateway pacienteGateway;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

    //@Scheduled(cron = "0 0 * * * *") // Executa a cada hora (no minuto 0)
    @Scheduled(fixedRate = 6000) // Para testes, executa a cada 6 segundos
    public void enviarLembreteTratamentos() {
        try {
            LocalDate hoje = LocalDate.now();
            int horaAtual = LocalDateTime.now().getHour();
            String enumHora = "H" + (horaAtual == 0 ? "00" : horaAtual);
            HorarioEnum horario = HorarioEnum.valueOf(enumHora);

            log.info("Iniciando verificação de tratamentos para: {} às {}h", hoje, horaAtual);

            List<TratamentoEntity> tratamentosAtivos = tratamentoGateway.findByPeriodoAndHorario(hoje, horario);
            log.info("Encontrados {} tratamentos ativos", tratamentosAtivos.size());

            if (tratamentosAtivos.isEmpty()) {
                log.info("Nenhum tratamento encontrado para este horário");
                return;
            }

            tratamentosAtivos.forEach(this::enviarLembreteTratamento);

        } catch (Exception e) {
            log.error("Erro ao processar lembretes de tratamento", e);
        }
    }

    @Transactional
    private void enviarLembreteTratamento(TratamentoEntity tratamento) {
        try {
            String medicamentos = tratamento.getMedicamentos().stream()
                    .map(med -> med.getNomeMedicacao())
                    .collect(Collectors.joining(", "));

            String conteudo = String.format("Lembrete de medicação: Tomar %s de %s",
                    tratamento.getDosagem(),
                    medicamentos);

            Paciente paciente = pacienteGateway.getPacientePorId(tratamento.getIdPaciente());

            enviarEmailLembretePaciente(conteudo, paciente);
            //enviarSmsLembretePaciente(conteudo, paciente);
            //enviarWhatsAppLembretePaciente(conteudo, paciente);

            List<ContatoAnjo> contatosAnjo = pacienteGateway.getContatoAnjoPorPaciente(tratamento.getIdPaciente());
            contatosAnjo.forEach(contatoAnjo -> {
                String conteudoAnjo = String.format("Olá %s, você é contato de emergência do paciente %s. O paciente tem que tomar a medicação %s de %s",
                        contatoAnjo.getNomeContatoAnjo(), paciente.getNomePaciente(), tratamento.getDosagem(), medicamentos);
                enviarEmailLembrete(conteudoAnjo, contatoAnjo);
                //enviarSmsLembrete(conteudoAnjo, contatoAnjo);
                //enviarWhatsAppLembrete(conteudoAnjo, contatoAnjo);
            });

            log.info("Notificação enviada com sucesso para tratamento ID: {}", tratamento.getIdTratamento());
        } catch (Exception e) {
            log.error("Erro ao enviar notificação para tratamento ID: {}", tratamento.getIdTratamento(), e);
        }
    }

    private void enviarEmailLembrete(String conteudo, ContatoAnjo contatoAnjo) {
        Mensagem mensagem = new Mensagem();
        mensagem.setTipo("email");
        mensagem.setDestinatario(contatoAnjo.getEmailContatoAnjo());
        mensagem.setAssunto("Lembrete de Medicamento");
        mensagem.setConteudo(conteudo);
        mensagemService.enviarMensagem(mensagem);
        log.info("Email de lembrete enviado para o Anjo {}", contatoAnjo.getNomeContatoAnjo());
    }

    private void enviarSmsLembrete(String conteudo, ContatoAnjo contatoAnjo) {
        Mensagem mensagem = new Mensagem();
        mensagem.setTipo("sms");
        mensagem.setNumero(contatoAnjo.getTelefoneContatoAnjo());
        mensagem.setConteudo(conteudo);
        mensagemService.enviarMensagem(mensagem);
        log.info("SMS de lembrete enviado para o Anjo {}", contatoAnjo.getNomeContatoAnjo());
    }

    private void enviarWhatsAppLembrete(String conteudo, ContatoAnjo contatoAnjo) {
        Mensagem mensagem = new Mensagem();
        mensagem.setTipo("whatsapp");
        mensagem.setNumero(contatoAnjo.getTelefoneContatoAnjo());
        mensagem.setConteudo(conteudo);
        mensagemService.enviarMensagem(mensagem);
        log.info("WhatsApp de lembrete enviado para o Anjo {}", contatoAnjo.getNomeContatoAnjo());
    }

    private void enviarEmailLembretePaciente(String conteudo, Paciente paciente) {
        Mensagem mensagem = new Mensagem();
        mensagem.setTipo("email");
        mensagem.setDestinatario(paciente.getEmailPaciente());
        mensagem.setAssunto("Lembrete de Medicamento");
        mensagem.setConteudo(conteudo);
        mensagemService.enviarMensagem(mensagem);
        log.info("Email de lembrete enviado para o paciente {}", paciente.getNomePaciente());
    }

    private void enviarSmsLembretePaciente(String conteudo, Paciente paciente) {
        Mensagem mensagem = new Mensagem();
        mensagem.setTipo("sms");
        mensagem.setNumero(paciente.getTelefonePaciente());
        mensagem.setConteudo(conteudo);
        mensagemService.enviarMensagem(mensagem);
        log.info("SMS de lembrete enviado para o paciente {}", paciente.getNomePaciente());
    }

    private void enviarWhatsAppLembretePaciente(String conteudo, Paciente paciente) {
        Mensagem mensagem = new Mensagem();
        mensagem.setTipo("whatsapp");
        mensagem.setNumero(paciente.getTelefonePaciente());
        mensagem.setConteudo(conteudo);
        mensagemService.enviarMensagem(mensagem);
        log.info("WhatsApp de lembrete enviado para o paciente {}", paciente.getNomePaciente());
    }
}