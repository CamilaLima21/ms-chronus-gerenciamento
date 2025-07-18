package br.com.chronus.gerenciamento.application.usecase.conteudo;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetConteudoPortalSaudeByFiltro {

    private final ConteudoPortalSaudeGateway conteudoPortalSaudeGateway;

    public List<ConteudoPortalSaude> execute(EnumFiltroPortalSaude filtro) {
        return conteudoPortalSaudeGateway.findByFiltro(filtro);
    }
}