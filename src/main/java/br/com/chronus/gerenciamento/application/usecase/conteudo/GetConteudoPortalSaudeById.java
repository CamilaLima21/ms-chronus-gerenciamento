package br.com.chronus.gerenciamento.application.usecase.conteudo;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.conteudo.exception.ConteudoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetConteudoPortalSaudeById {

    private final ConteudoPortalSaudeGateway gateway;

    public ConteudoPortalSaude execute(final int id) {
        return gateway.findById(id)
                .orElseThrow(() -> new ConteudoNaoEncontradoException(id));
    }
}
