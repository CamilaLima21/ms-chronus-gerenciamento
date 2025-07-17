package br.com.chronus.gerenciamento.application.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnumStatusExameTest {

    @Test
    void deveConterTodosOsValoresEsperados() {
        assertNotNull(EnumStatusExame.AGENDADO);
        assertNotNull(EnumStatusExame.EM_ANDAMENTO);
        assertNotNull(EnumStatusExame.CONCLUIDO);
        assertNotNull(EnumStatusExame.CANCELADO);
        assertNotNull(EnumStatusExame.NAO_REALIZADO);
        assertNotNull(EnumStatusExame.AGUARDANDO_RESULTADO);
        assertNotNull(EnumStatusExame.RESULTADO_DISPONIVEL);
        assertNotNull(EnumStatusExame.REAGENDADO);
    }

    @Test
    void deveRetornarDescricaoCorreta() {
        assertEquals("Exame agendado", EnumStatusExame.AGENDADO.getDescricao());
        assertEquals("Exame em andamento", EnumStatusExame.EM_ANDAMENTO.getDescricao());
        assertEquals("Exame concluído", EnumStatusExame.CONCLUIDO.getDescricao());
        assertEquals("Exame cancelado", EnumStatusExame.CANCELADO.getDescricao());
        assertEquals("Exame não realizado", EnumStatusExame.NAO_REALIZADO.getDescricao());
        assertEquals("Aguardando resultado", EnumStatusExame.AGUARDANDO_RESULTADO.getDescricao());
        assertEquals("Resultado disponível", EnumStatusExame.RESULTADO_DISPONIVEL.getDescricao());
        assertEquals("Exame reagendado", EnumStatusExame.REAGENDADO.getDescricao());
    }

    @Test
    void devePermitirValueOf() {
        for (EnumStatusExame status : EnumStatusExame.values()) {
            EnumStatusExame resultado = EnumStatusExame.valueOf(status.name());
            assertEquals(status, resultado);
        }
    }
}
