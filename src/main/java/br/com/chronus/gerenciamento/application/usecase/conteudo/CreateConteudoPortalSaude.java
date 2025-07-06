package br.com.chronus.gerenciamento.application.usecase.conteudo;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.conteudo.exception.ConteudoExistenteException;
import br.com.chronus.gerenciamento.application.usecase.conteudo.exception.ConteudoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateConteudoPortalSaude {

    private final ConteudoPortalSaudeGateway gateway;

    public ConteudoPortalSaude execute(final ConteudoPortalSaude request) {

        final var existing = gateway.findById(request.getId());

        if (existing.isPresent()) {
            throw new ConteudoExistenteException(request.getId());
        }

        return gateway.save(request);
    }
}