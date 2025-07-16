package br.com.chronus.gerenciamento.application.usecase.enfermidade;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.dto.enfermidades.EnfermidadeRequest;
import br.com.chronus.gerenciamento.application.gateway.EnfermidadeGateway;
import br.com.chronus.gerenciamento.application.usecase.enfermidade.exception.EnfermidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateEnfermidade {

    private final EnfermidadeGateway gateway;

    public Enfermidade execute(final int idEnfermidade, final EnfermidadeRequest enfermidadeRequest) {
        final var enfermidadeFound =
                gateway
                        .findEnfermidadeById(idEnfermidade)
                        .orElseThrow(() -> new EnfermidadeNaoEncontradaException(idEnfermidade));

        enfermidadeFound.setEnfermidade(enfermidadeRequest.getEnfermidade());
        enfermidadeFound.setDescricaoEnfermidade(enfermidadeRequest.getDescricaoEnfermidade());
        enfermidadeFound.setCid(enfermidadeRequest.getCid());

        return gateway.update(enfermidadeFound);
    }
}