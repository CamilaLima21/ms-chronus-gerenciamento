package br.com.chronus.gerenciamento.application.usecase.exame;

import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import br.com.chronus.gerenciamento.application.usecase.exame.exception.ExameNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteExame {

    private final ExameGateway gateway;

    public void execute(final int idExame) {
        gateway.findExameById(idExame)
                .orElseThrow(() -> new ExameNaoEncontradoException(idExame));
        gateway.delete(idExame);
    }
}