package br.com.chronus.gerenciamento.infrastructure.service;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.domain.ContatoAnjo;
import br.com.chronus.gerenciamento.application.domain.Mensagem;
import br.com.chronus.gerenciamento.application.domain.Paciente;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.service.ConsultaSchedulerService;
import br.com.chronus.gerenciamento.application.service.MensagemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultaSchedulerServiceImpl implements ConsultaSchedulerService {

    private final ConsultaGateway consultaGateway;
    private final MensagemService mensagemService;
    private final PacienteGateway pacienteGateway;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

    //@Scheduled(cron = "0 0 8 * * *") // Executa todos os dias às 8h
    @Scheduled(fixedRate = 6000) // Para testes, executa a cada 6 segundos
    public void enviarLembreteConsultas() {
        log.info("Iniciando envio de lembretes de consultas...");
        LocalDate amanha = LocalDate.now().plusDays(1);
        List<Consulta> consultasAmanha = consultaGateway.findByDataConsulta(amanha);

        consultasAmanha.forEach(consulta -> {

            Paciente paciente = pacienteGateway.getPacientePorId(consulta.getIdPaciente());

            String conteudo = String.format("Lembrete: Você tem uma consulta amanhã %s",
                    consulta.getDataHoraConsulta().format(formatter));

            enviarEmailLembretePaciente(conteudo, paciente);
            //enviarSmsLembretePaciente(conteudo, paciente);
            //enviarWhatsAppLembretePaciente(conteudo, paciente);

            List<ContatoAnjo> contatosAnjo = pacienteGateway.getContatoAnjoPorPaciente(consulta.getIdPaciente());
            contatosAnjo.forEach(contatoAnjo -> {
                String conteudoAnjo = String.format("Olá %s, você é contato de emergência do paciente %s. O paciente está agendado para uma consulta amanhã %s",
                        contatoAnjo.getNomeContatoAnjo(), paciente.getNomePaciente(), consulta.getDataHoraConsulta().format(formatter));
                enviarEmailLembrete(conteudoAnjo, contatoAnjo);
                //enviarSmsLembrete(conteudoAnjo, contatoAnjo);
                //enviarWhatsAppLembrete(conteudoAnjo, contatoAnjo);
            });
        });
    }

    private void enviarEmailLembrete(String conteudo, ContatoAnjo contatoAnjo) {
        Mensagem mensagem = new Mensagem();
        mensagem.setTipo("email");
        mensagem.setDestinatario(contatoAnjo.getEmailContatoAnjo());
        mensagem.setAssunto("Lembrete de Consulta");
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
        mensagem.setAssunto("Lembrete de Consulta");
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