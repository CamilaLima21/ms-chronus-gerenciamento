package br.com.chronus.gerenciamento.application.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumFiltroPortalSaudeTest {

    @Test
    void deveConterValoresEsperados() {
        assertTrue(containsEnumFiltro("CUIDADOS_ASMA"));
        assertTrue(containsEnumFiltro("PREVENCAO_CANCERES"));
        assertTrue(containsEnumFiltro("PLANO_ALIMENTAR_OBESIDADE"));
        assertTrue(containsEnumFiltro("EDUCACAO_EM_SAUDE"));
    }

    @Test
    void devePermitirValueOf() {
        for (EnumFiltroPortalSaude filtro : EnumFiltroPortalSaude.values()) {
            EnumFiltroPortalSaude result = EnumFiltroPortalSaude.valueOf(filtro.name());
            assertEquals(filtro, result);
        }
    }

    @Test
    void deveRetornarEnfermidadeCorreta() {
        assertEquals(EnumEnfermidade.ASMA, EnumFiltroPortalSaude.CUIDADOS_ASMA.getEnfermidade());
        assertEquals(EnumEnfermidade.CANCERES, EnumFiltroPortalSaude.PREVENCAO_CANCERES.getEnfermidade());
        assertEquals(EnumEnfermidade.OBESIDADE, EnumFiltroPortalSaude.PLANO_ALIMENTAR_OBESIDADE.getEnfermidade());
        assertEquals(EnumEnfermidade.OUTROS, EnumFiltroPortalSaude.EDUCACAO_EM_SAUDE.getEnfermidade());
    }

    private boolean containsEnumFiltro(String nome) {
        for (EnumFiltroPortalSaude e : EnumFiltroPortalSaude.values()) {
            if (e.name().equals(nome)) {
                return true;
            }
        }
        return false;
    }
}
