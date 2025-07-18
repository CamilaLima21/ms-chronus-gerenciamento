package br.com.chronus.gerenciamento.infrastructure.service;

import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import br.com.chronus.gerenciamento.application.service.NotificacaoService;
import br.com.chronus.gerenciamento.application.service.TratamentoSchedulerService;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.TratamentoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TratamentoSchedulerServiceImpl implements TratamentoSchedulerService {

    private final TratamentoGateway tratamentoGateway;
    private final NotificacaoService notificacaoService;

    @Scheduled(cron = "0 0 * * * *") // Executa no início de cada hora
    public void enviarLembreteTratamentos() {
        LocalDate hoje = LocalDate.now();
        HorarioEnum horaAtual = HorarioEnum.valueOf("H" + LocalDateTime.now().getHour());

        List<TratamentoEntity> tratamentosAtivos = tratamentoGateway.findByPeriodoAndHorario(
                hoje,
                horaAtual
        );

        tratamentosAtivos.forEach(tratamento ->
                notificacaoService.enviarNotificacao(
                        tratamento.get(),
                        String.format("Hora do seu tratamento! Tomar %s de %s",
                                tratamento.getDosagem(),
                                tratamento.getMedicamentos().stream()
                                        .map(MedicacaoEntity::getNomeMedicacao)
                                        .collect(Collectors.joining(", "))
                        )
                )
        );
    }
}