package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.checkup.exception.CheckUpSaudeNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindCheckUpSaudeById {

    private final CheckUpSaudeGateway gateway;

    public CheckUpSaude execute(final Integer idCheckUp) {
        return gateway.findById(idCheckUp)
                .orElseThrow(() -> new CheckUpSaudeNaoEncontradoException(idCheckUp));
    }
}
