package br.com.chronus.gerenciamento.application.usecase.conteudo;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.conteudo.exception.ConteudoExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateConteudoPortalSaude {

    private final ConteudoPortalSaudeGateway gateway;

    public ConteudoPortalSaude execute(final ConteudoPortalSaude request) {
        return gateway.save(request);
    }
}