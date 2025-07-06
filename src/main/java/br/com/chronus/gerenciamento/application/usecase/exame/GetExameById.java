package br.com.chronus.gerenciamento.application.usecase.exame;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import br.com.chronus.gerenciamento.application.usecase.exame.exception.ExameNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetExameById {

    private final ExameGateway gateway;

    public Exame execute(final int idExame) {
        return gateway.findExameById(idExame)
                .orElseThrow(() -> new ExameNaoEncontradoException(idExame));
    }
}