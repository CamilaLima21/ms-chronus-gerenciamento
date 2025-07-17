package br.com.chronus.gerenciamento.application.usecase.consulta.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PacienteNaoEncontradoExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemCorreta() {
        Integer idPaciente = 123;
        PacienteNaoEncontradoException exception = new PacienteNaoEncontradoException(idPaciente);

        String mensagemEsperada = "Paciente com ID " + idPaciente + " não encontrado.";
        assertEquals(mensagemEsperada, exception.getMessage());
    }
}
