package br.com.chronus.gerenciamento.application.usecase.enfermidade;

import br.com.chronus.gerenciamento.application.gateway.EnfermidadeGateway;
import br.com.chronus.gerenciamento.application.usecase.enfermidade.exception.EnfermidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteEnfermidade {

    private final EnfermidadeGateway gateway;

    public void execute(final int idEnfermidade) {
        final var enfermidade = gateway.findEnfermidadeById(idEnfermidade)
                .orElseThrow(() -> new EnfermidadeNaoEncontradaException(idEnfermidade));

        gateway.delete(enfermidade.getIdEnfermidade());
    }
}