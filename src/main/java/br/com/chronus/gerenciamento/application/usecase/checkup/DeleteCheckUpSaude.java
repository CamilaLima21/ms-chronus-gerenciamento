package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.checkup.exception.CheckUpSaudeNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCheckUpSaude {

    private final CheckUpSaudeGateway gateway;

    public void execute(final Integer idCheckUp) {
        final var checkUp = gateway.findById(idCheckUp)
                .orElseThrow(() -> new CheckUpSaudeNaoEncontradoException(idCheckUp));

        gateway.delete(idCheckUp);
    }
}
