package br.com.chronus.gerenciamento.application.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnumExameTest {

    @Test
    void testValoresPresentes() {
        EnumExame[] exames = EnumExame.values();

        assertTrue(containsEnumExame(exames, "ESPIROMETRIA_ASMA"));
        assertTrue(containsEnumExame(exames, "PAPANICOLAU_CANCERES"));
        assertTrue(containsEnumExame(exames, "IMC_OBESIDADE"));

    }

    @Test
    void testValueOfFunciona() {
        for (EnumExame exame : EnumExame.values()) {
            EnumExame result = EnumExame.valueOf(exame.name());
            assertEquals(exame, result);
        }
    }

    @Test
    void testGetEnfermidade() {
        assertEquals(EnumEnfermidade.ASMA, EnumExame.ESPIROMETRIA_ASMA.getEnfermidade());
        assertEquals(EnumEnfermidade.CANCERES, EnumExame.PAPANICOLAU_CANCERES.getEnfermidade());
        assertEquals(EnumEnfermidade.OBESIDADE, EnumExame.IMC_OBESIDADE.getEnfermidade());

    }

    private boolean containsEnumExame(EnumExame[] array, String nome) {
        for (EnumExame e : array) {
            if (e.name().equals(nome)) {
                return true;
            }
        }
        return false;
    }
}
