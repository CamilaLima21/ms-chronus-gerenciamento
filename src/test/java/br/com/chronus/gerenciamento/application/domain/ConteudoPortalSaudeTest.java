package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConteudoPortalSaudeTest {

    @Test
    void deveCriarConteudoPortalSaudeComDadosCorretos() {

        EnumFiltroPortalSaude filtro = EnumFiltroPortalSaude.ORIENTACOES_DIETA_DIABETES;
        List<String> conteudos = List.of(
                "Evitar açúcar refinado",
                "Manter dieta com baixo índice glicêmico",
                "Acompanhamento nutricional mensal"
        );

        ConteudoPortalSaude conteudoPortal = ConteudoPortalSaude.createConteudoPortalSaude(null, filtro, conteudos);

        assertNull(conteudoPortal.getId());
        assertEquals(filtro, conteudoPortal.getFiltroPortalSaude());
        assertEquals(EnumEnfermidade.DIABETES, filtro.getEnfermidade());
        assertEquals(3, conteudoPortal.getConteudos().size());
        assertTrue(conteudoPortal.getConteudos().contains("Evitar açúcar refinado"));
    }
}
