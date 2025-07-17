package br.com.chronus.gerenciamento.application.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HorarioEnumTest {

    @Test
    void deveConterTodosOsHorarios() {
        for (int i = 0; i < 24; i++) {
            String enumName = String.format("H%02d", i);
            HorarioEnum horario = HorarioEnum.valueOf(enumName);
            assertNotNull(horario, "Enum " + enumName + " deve existir");
        }
    }

    @Test
    void deveRetornarEnumCorretoComValueOf() {
        assertEquals(HorarioEnum.H00, HorarioEnum.valueOf("H00"));
        assertEquals(HorarioEnum.H12, HorarioEnum.valueOf("H12"));
        assertEquals(HorarioEnum.H23, HorarioEnum.valueOf("H23"));
    }
}
