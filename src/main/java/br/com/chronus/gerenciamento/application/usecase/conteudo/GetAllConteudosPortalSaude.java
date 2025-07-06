package br.com.chronus.gerenciamento.application.usecase.conteudo;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllConteudosPortalSaude {

    private final ConteudoPortalSaudeGateway gateway;

    public List<ConteudoPortalSaude> execute() {
        return gateway.findAll();
    }
}
