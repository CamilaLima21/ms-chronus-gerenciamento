package br.com.chronus.gerenciamento.application.usecase.conteudo.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConteudoNaoEncontradoExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemEErrorCodeCorretos() {

        Integer id = 123;

        ConteudoNaoEncontradoException exception = new ConteudoNaoEncontradoException(id);

        assertTrue(exception instanceof BusinessException);
        assertEquals("Conteúdo com id [123] não encontrado.", exception.getMessage());
        assertEquals("not_found", exception.getErrorCode());
    }
}
