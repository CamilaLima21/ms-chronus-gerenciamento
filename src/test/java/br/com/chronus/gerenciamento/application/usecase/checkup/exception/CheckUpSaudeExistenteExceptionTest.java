package br.com.chronus.gerenciamento.application.usecase.checkup.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckUpSaudeExistenteExceptionTest {

    @Test
    void testExceptionMessageAndErrorCode() {
        Integer idCheckUp = 123;
        CheckUpSaudeExistenteException exception = new CheckUpSaudeExistenteException(idCheckUp);

        String expectedMessage = String.format("Check-up de saúde com id [%s] já existe.", idCheckUp);
        String expectedErrorCode = "already_exists";

        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(expectedErrorCode, exception.getErrorCode());
    }
}

