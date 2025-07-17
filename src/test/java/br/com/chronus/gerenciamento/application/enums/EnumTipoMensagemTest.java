package br.com.chronus.gerenciamento.application.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnumTipoMensagemTest {

    @Test
    void deveConterTodosOsTiposDeMensagem() {
        assertNotNull(EnumTipoMensagem.SMS);
        assertNotNull(EnumTipoMensagem.WHATSAPP);
        assertNotNull(EnumTipoMensagem.EMAIL);
    }

    @Test
    void deveRetornarEnumCorretoAoUsarValueOf() {
        EnumTipoMensagem sms = EnumTipoMensagem.valueOf("SMS");
        EnumTipoMensagem whatsapp = EnumTipoMensagem.valueOf("WHATSAPP");
        EnumTipoMensagem email = EnumTipoMensagem.valueOf("EMAIL");

        assertEquals(EnumTipoMensagem.SMS, sms);
        assertEquals(EnumTipoMensagem.WHATSAPP, whatsapp);
        assertEquals(EnumTipoMensagem.EMAIL, email);
    }
}
