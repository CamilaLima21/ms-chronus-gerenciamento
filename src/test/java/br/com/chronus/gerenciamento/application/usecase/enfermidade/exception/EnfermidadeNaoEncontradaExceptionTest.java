package br.com.chronus.gerenciamento.application.usecase.enfermidade.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnfermidadeNaoEncontradaExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemECodigoCorretos() {

        Integer id = 42;

        EnfermidadeNaoEncontradaException exception = new EnfermidadeNaoEncontradaException(id);

        String expectedMessage = String.format("Enfermidade with id [%s] not found.", id);
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals("not_found", exception.getErrorCode());
    }
}
