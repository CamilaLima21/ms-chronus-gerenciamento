package br.com.chronus.gerenciamento.infrastructure.service;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.service.ConsultaSchedulerService;
import br.com.chronus.gerenciamento.application.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultaSchedulerServiceImpl implements ConsultaSchedulerService {

    private final ConsultaGateway consultaGateway;
    private final NotificacaoService notificacaoService;

    @Scheduled(cron = "0 0 8 * * *") // Executa todos os dias às 8h
    public void enviarLembreteConsultas() {
        LocalDate amanha = LocalDate.now().plusDays(1);

        List<Consulta> consultasAmanha = consultaGateway.findByDataConsulta(amanha);

        consultasAmanha.forEach(consulta ->
                notificacaoService.enviarNotificacao(
                        consulta.getPacienteId(),
                        String.format("Lembrete: Você tem uma consulta amanhã às %s",
                                consulta.getHorario())
                )
        );
    }
}