package br.com.chronus.gerenciamento.application.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnumStatusConsultaTest {

    @Test
    void deveConterTodosOsValoresEsperados() {
        assertTrue(containsEnumStatus("CONFIRMADA"));
        assertTrue(containsEnumStatus("PENDENTE"));
        assertTrue(containsEnumStatus("CANCELADA"));
    }

    @Test
    void devePermitirValueOf() {
        for (EnumStatusConsulta status : EnumStatusConsulta.values()) {
            EnumStatusConsulta result = EnumStatusConsulta.valueOf(status.name());
            assertEquals(status, result);
        }
    }

    private boolean containsEnumStatus(String nome) {
        for (EnumStatusConsulta e : EnumStatusConsulta.values()) {
            if (e.name().equals(nome)) {
                return true;
            }
        }
        return false;
    }
}
