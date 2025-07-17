package br.com.chronus.gerenciamento.application.usecase.exame.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExameNaoEncontradoExceptionTest {

    @Test
    void deveCriarExameNaoEncontradoExceptionComMensagemFormatada() {
        Integer idExame = 99;
        ExameNaoEncontradoException exception = new ExameNaoEncontradoException(idExame);

        assertEquals("Exame com id [99] não encontrado.", exception.getMessage());
        assertEquals("not_found", exception.getErrorCode());
    }
}
