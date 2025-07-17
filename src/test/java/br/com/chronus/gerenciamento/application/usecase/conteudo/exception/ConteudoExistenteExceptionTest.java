package br.com.chronus.gerenciamento.application.usecase.conteudo.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConteudoExistenteExceptionTest {

    @Test
    void deveConstruirExcecaoComMensagemEErrorCodeCorretos() {

        Integer id = 123;
        String expectedMessage = "Conteúdo com id [123] já existe.";
        String expectedErrorCode = "already_exists";

        ConteudoExistenteException exception = new ConteudoExistenteException(id);

        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(expectedErrorCode, exception.getErrorCode());
    }
}
