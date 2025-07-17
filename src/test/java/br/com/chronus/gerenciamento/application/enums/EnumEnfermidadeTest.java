package br.com.chronus.gerenciamento.application.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnumEnfermidadeTest {

    @Test
    void testEnumValoresPresentes() {
        EnumEnfermidade[] valores = EnumEnfermidade.values();

        assertEquals(13, valores.length, "Deve conter exatamente 13 valores");

        assertTrue(contains(valores, "ASMA"));
        assertTrue(contains(valores, "CANCERES"));
        assertTrue(contains(valores, "COLESTEROL_ALTO"));
        assertTrue(contains(valores, "DIABETES"));
        assertTrue(contains(valores, "DOENCA_CARDIO_VASCULAR"));
        assertTrue(contains(valores, "DOENCA_HEPATICA_CRONICA"));
        assertTrue(contains(valores, "DOENCA_PULMONAR_OBSTRUTIVA_CRONICA"));
        assertTrue(contains(valores, "DOENCA_RENAL_CRONICA"));
        assertTrue(contains(valores, "DOENCA_REUMATICA"));
        assertTrue(contains(valores, "HIV_AIDS"));
        assertTrue(contains(valores, "HIPERTENSAO_ARTERIAL"));
        assertTrue(contains(valores, "OBESIDADE"));
        assertTrue(contains(valores, "OUTROS"));
    }

    @Test
    void testValueOfFunciona() {
        for (EnumEnfermidade enfermidade : EnumEnfermidade.values()) {
            EnumEnfermidade result = EnumEnfermidade.valueOf(enfermidade.name());
            assertEquals(enfermidade, result);
        }
    }

    private boolean contains(EnumEnfermidade[] array, String nome) {
        for (EnumEnfermidade e : array) {
            if (e.name().equals(nome)) {
                return true;
            }
        }
        return false;
    }
}
