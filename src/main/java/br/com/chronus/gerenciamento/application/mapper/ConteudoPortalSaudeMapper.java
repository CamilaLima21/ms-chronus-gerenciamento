package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.dto.portal.ConteudoPortalSaudeRequest;
import org.springframework.stereotype.Component;

@Component
public class ConteudoPortalSaudeMapper {

    public ConteudoPortalSaude toDomain(ConteudoPortalSaudeRequest dto, Integer id) {
        return ConteudoPortalSaude.builder()
                .id(id)
                .filtroPortalSaude(dto.getFiltroPortalSaude())
                .conteudos(dto.getConteudos())
                .build();
    }

}