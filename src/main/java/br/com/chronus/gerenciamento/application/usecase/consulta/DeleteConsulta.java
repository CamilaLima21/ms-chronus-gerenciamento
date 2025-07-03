package br.com.chronus.gerenciamento.application.usecase.consulta;

import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ConsultaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DeleteConsulta {

    private final ConsultaGateway consultaGateway;

    public void execute(final Integer idConsulta, final LocalDate dataHoraConsulta) {
        final var consulta = consultaGateway.getConsultaById(idConsulta)
                .orElseThrow(() -> new ConsultaNaoEncontradaException(idConsulta));

        consultaGateway.deleteConsulta(consulta.getIdConsulta());
    }
}
