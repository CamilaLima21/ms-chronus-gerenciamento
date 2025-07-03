package br.com.chronus.gerenciamento.application.usecase.consulta;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ConsultaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetConsulta {

    private final ConsultaGateway consultaGateway;

    public Consulta execute(final Integer idConsulta) {
        return consultaGateway.getConsultaById(idConsulta)
                .orElseThrow(() -> new ConsultaNaoEncontradaException(idConsulta));
    }
}
