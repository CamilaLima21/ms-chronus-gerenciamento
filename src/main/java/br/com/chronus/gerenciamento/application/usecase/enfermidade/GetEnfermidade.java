package br.com.chronus.gerenciamento.application.usecase.enfermidade;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.gateway.EnfermidadeGateway;
import br.com.chronus.gerenciamento.application.usecase.enfermidade.exception.EnfermidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetEnfermidade {

    private final EnfermidadeGateway gateway;

    public Enfermidade execute(final int idEnfermidade) {
        return gateway.findEnfermidadeById(idEnfermidade)
                .orElseThrow(() -> new EnfermidadeNaoEncontradaException(idEnfermidade));
    }
}