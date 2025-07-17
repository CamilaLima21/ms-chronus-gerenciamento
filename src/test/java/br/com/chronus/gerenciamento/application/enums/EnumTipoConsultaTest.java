package br.com.chronus.gerenciamento.application.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnumTipoConsultaTest {

    @Test
    void deveConterTodosOsTiposDeConsulta() {
        assertNotNull(EnumTipoConsulta.PRESENCIAL);
        assertNotNull(EnumTipoConsulta.TELEMEDICINA);
    }

    @Test
    void deveRetornarEnumCorretoAoUsarValueOf() {
        EnumTipoConsulta presencial = EnumTipoConsulta.valueOf("PRESENCIAL");
        EnumTipoConsulta telemedicina = EnumTipoConsulta.valueOf("TELEMEDICINA");

        assertEquals(EnumTipoConsulta.PRESENCIAL, presencial);
        assertEquals(EnumTipoConsulta.TELEMEDICINA, telemedicina);
    }
}
