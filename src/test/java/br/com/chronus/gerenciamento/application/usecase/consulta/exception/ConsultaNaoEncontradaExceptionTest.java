package br.com.chronus.gerenciamento.application.usecase.consulta.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConsultaNaoEncontradaExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemCorreta() {
        Integer idConsulta = 42;
        ConsultaNaoEncontradaException exception = new ConsultaNaoEncontradaException(idConsulta);

        String mensagemEsperada = String.format("Consulta com id [%s] não encontrada.", idConsulta);

        assertThat(exception.getMessage()).isEqualTo(mensagemEsperada);
        assertThat(exception.getErrorCode()).isEqualTo("consulta.nao.encontrada");
    }
}
