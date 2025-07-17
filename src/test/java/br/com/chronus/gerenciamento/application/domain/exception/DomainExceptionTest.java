package br.com.chronus.gerenciamento.application.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomainExceptionTest {

    @Test
    void deveCriarDomainExceptionComMensagemEErrorCode() {

        String mensagem = "Erro no domínio";
        String codigoErro = "erro_customizado";

        DomainException exception = new DomainException(mensagem, codigoErro);

        assertEquals(mensagem, exception.getMessage());
        assertEquals(mensagem, exception.getMessage());
        assertEquals(mensagem, exception.getMessage());
        assertEquals(codigoErro, exception.getErrorCode());
    }

    @Test
    void deveCriarDomainExceptionComMensagemApenas() {

        String mensagem = "Erro padrão do domínio";

        DomainException exception = new DomainException(mensagem);

        assertEquals(mensagem, exception.getMessage());
        assertEquals(mensagem, exception.getMessage());
        assertEquals("domain_exception", exception.getErrorCode());
    }

    @Test
    void deveSerRuntimeException() {
        DomainException exception = new DomainException("teste");
        assertTrue(exception instanceof RuntimeException);
    }
}
