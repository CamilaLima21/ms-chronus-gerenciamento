package br.com.chronus.gerenciamento.application.usecase.exame.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExameExistenteExceptionTest {

    @Test
    void deveCriarExameExistenteExceptionComMensagemFormatada() {
        Integer idExame = 42;
        ExameExistenteException exception = new ExameExistenteException(idExame);

        assertEquals("Exame com id [42] já existe.", exception.getMessage());
        assertEquals("already_exists", exception.getErrorCode());
    }
}
