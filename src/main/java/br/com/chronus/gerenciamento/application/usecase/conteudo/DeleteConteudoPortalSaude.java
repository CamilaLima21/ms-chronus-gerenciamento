package br.com.chronus.gerenciamento.application.usecase.conteudo;

import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.conteudo.exception.ConteudoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteConteudoPortalSaude {

    private final ConteudoPortalSaudeGateway gateway;

    public void execute(final int id) {
        gateway.findById(id)
                .orElseThrow(() -> new ConteudoNaoEncontradoException(id));
        gateway.delete(id);
    }
}
