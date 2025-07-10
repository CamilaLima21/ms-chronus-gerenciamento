package br.com.chronus.gerenciamento.application.usecase.enfermidade;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.dto.enfermidades.UpdateEnfermidadeRequest;
import br.com.chronus.gerenciamento.application.gateway.EnfermidadeGateway;
import br.com.chronus.gerenciamento.application.usecase.enfermidade.exception.EnfermidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateEnfermidade {

    private final EnfermidadeGateway gateway;

    public Enfermidade execute(final int idEnfermidade, final UpdateEnfermidadeRequest updateEnfermidadeRequest) {
        final var enfermidadeFound =
                gateway
                        .findEnfermidadeById(idEnfermidade)
                        .orElseThrow(() -> new EnfermidadeNaoEncontradaException(idEnfermidade));

        enfermidadeFound.setEnfermidade(updateEnfermidadeRequest.getEnfermidade());
        enfermidadeFound.setDescricaoEnfermidade(updateEnfermidadeRequest.getDescricaoEnfermidade());
        enfermidadeFound.setCid(updateEnfermidadeRequest.getCid());

        return gateway.update(enfermidadeFound);
    }
}