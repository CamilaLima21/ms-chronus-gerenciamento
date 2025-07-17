package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.dto.portal.ConteudoPortalSaudeRequest;
import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConteudoPortalSaudeMapperTest {

    private final ConteudoPortalSaudeMapper mapper = new ConteudoPortalSaudeMapper();

    @Test
    void toDomain_shouldMapFieldsCorrectly() {
        ConteudoPortalSaudeRequest dto = new ConteudoPortalSaudeRequest();
        dto.setFiltroPortalSaude(EnumFiltroPortalSaude.CUIDADOS_ASMA);
        dto.setConteudos(List.of("Conteúdo 1", "Conteúdo 2"));

        Integer id = 123;

        ConteudoPortalSaude domain = mapper.toDomain(dto, id);

        assertNotNull(domain);
        assertEquals(id, domain.getId());
        assertEquals(dto.getFiltroPortalSaude(), domain.getFiltroPortalSaude());
        assertEquals(dto.getConteudos(), domain.getConteudos());
    }
}
