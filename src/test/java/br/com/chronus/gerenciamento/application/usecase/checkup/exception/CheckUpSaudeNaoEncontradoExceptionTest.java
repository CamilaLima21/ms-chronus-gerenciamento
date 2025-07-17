package br.com.chronus.gerenciamento.application.usecase.checkup.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckUpSaudeNaoEncontradoExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemEErroCodeCorretos() {

        Integer id = 42;

        CheckUpSaudeNaoEncontradoException exception = new CheckUpSaudeNaoEncontradoException(id);

        assertEquals("Check-up de saúde com id [42] não encontrado.", exception.getMessage());
        assertEquals("not_found", exception.getErrorCode());
    }
}
