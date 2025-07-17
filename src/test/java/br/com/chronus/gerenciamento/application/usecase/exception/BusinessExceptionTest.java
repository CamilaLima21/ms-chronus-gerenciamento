package br.com.chronus.gerenciamento.application.usecase.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionTest {

    @Test
    void deveCriarBusinessExceptionComMensagemECodigoDeErro() {

        String mensagemEsperada = "Erro de negócio";
        String codigoErroEsperado = "BUSINESS_001";

        BusinessException exception = new BusinessException(mensagemEsperada, codigoErroEsperado);

        assertEquals(mensagemEsperada, exception.getMessage());
        assertEquals(mensagemEsperada, exception.getMessage());
        assertEquals(codigoErroEsperado, exception.getErrorCode());
    }
}
