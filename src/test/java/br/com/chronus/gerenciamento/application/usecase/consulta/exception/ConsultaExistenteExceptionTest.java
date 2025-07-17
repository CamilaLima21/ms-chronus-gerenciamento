package br.com.chronus.gerenciamento.application.usecase.consulta.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ConsultaExistenteExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemCorreta() {
        Integer idConsulta = 123;
        LocalDate dataHoraConsulta = LocalDate.of(2025, 7, 17);

        ConsultaExistenteException exception = new ConsultaExistenteException(idConsulta, dataHoraConsulta);

        String mensagemEsperada = String.format(
                "Consulta [%s] já cadastrado com o ID [%s] informado.",
                idConsulta, dataHoraConsulta
        );

        assertThat(exception.getMessage()).isEqualTo(mensagemEsperada);
        assertThat(exception.getErrorCode()).isEqualTo("consulta.existente");
    }
}
