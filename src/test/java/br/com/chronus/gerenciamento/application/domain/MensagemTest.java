package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.EnumTipoMensagem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MensagemTest {

    @Test
    void deveCriarMensagemComMetodoCreate() {

        String destino = "user@example.com";
        EnumTipoMensagem tipo = EnumTipoMensagem.EMAIL;
        String conteudo = "Teste de mensagem";

        Mensagem mensagem = Mensagem.createMensagem(destino, tipo, conteudo);

        assertNull(mensagem.getId());
        assertEquals(destino, mensagem.getDestino());
        assertEquals(tipo, mensagem.getTipoMensagem());
        assertEquals(conteudo, mensagem.getConteudo());
    }

    @Test
    void deveCriarMensagemComConstrutorEAlterarCamposComSetters() {

        Mensagem mensagem = new Mensagem();

        mensagem.setId(100L);
        mensagem.setDestino("11999999999");
        mensagem.setTipoMensagem(EnumTipoMensagem.SMS);
        mensagem.setConteudo("Mensagem SMS de teste");

        assertEquals(100L, mensagem.getId());
        assertEquals("11999999999", mensagem.getDestino());
        assertEquals(EnumTipoMensagem.SMS, mensagem.getTipoMensagem());
        assertEquals("Mensagem SMS de teste", mensagem.getConteudo());
    }
}
