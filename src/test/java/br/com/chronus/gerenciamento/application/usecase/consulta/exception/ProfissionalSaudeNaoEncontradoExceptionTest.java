package br.com.chronus.gerenciamento.application.usecase.consulta.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfissionalSaudeNaoEncontradoExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemCorreta() {
        Integer idProfissional = 456;
        ProfissionalSaudeNaoEncontradoException exception = new ProfissionalSaudeNaoEncontradoException(idProfissional);

        String mensagemEsperada = "Profissional de saúde com ID " + idProfissional + " não encontrado.";
        assertEquals(mensagemEsperada, exception.getMessage());
    }
}
