package br.com.chronus.gerenciamento.application.usecase.medicacao.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicacaoNaoEncontradaExceptionTest {

    @Test
    void deveConstruirExcecaoComMensagemECodigoCorretos() {

        Integer id = 42;

        MedicacaoNaoEncontradaException exception = new MedicacaoNaoEncontradaException(id);

        assertEquals("Medicação with id [42] not found.", exception.getMessage());
        assertEquals("not_found", exception.getErrorCode());
    }
}
