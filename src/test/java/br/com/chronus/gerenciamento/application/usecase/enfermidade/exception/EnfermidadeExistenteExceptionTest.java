package br.com.chronus.gerenciamento.application.usecase.enfermidade.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnfermidadeExistenteExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemECodigoCorretos() {

        Integer id = 10;
        String nome = "DIABETES";

        EnfermidadeExistenteException exception = new EnfermidadeExistenteException(id, nome);

        String expectedMessage = String.format("Enfermidade [%s] with id [%s] already exists.", nome, id);
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals("already_exists", exception.getErrorCode());
    }
}
