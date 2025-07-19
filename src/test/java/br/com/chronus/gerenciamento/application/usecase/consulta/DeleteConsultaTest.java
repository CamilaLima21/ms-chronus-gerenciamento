package br.com.chronus.gerenciamento.application.usecase.consulta;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ConsultaNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DeleteConsultaTest {

    @Mock
    private ConsultaGateway consultaGateway;

    @InjectMocks
    private DeleteConsulta useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveExcluirConsultaComSucesso() {
        Integer idConsulta = 1;
        LocalDateTime dataConsulta = LocalDateTime.now();

        Consulta consulta = Consulta.builder()
                .idConsulta(idConsulta)
                .dataHoraConsulta(dataConsulta)
                .build();

        when(consultaGateway.getConsultaById(idConsulta)).thenReturn(Optional.of(consulta));

        useCase.execute(idConsulta, dataConsulta);

        verify(consultaGateway).getConsultaById(idConsulta);
        verify(consultaGateway).deleteConsulta(idConsulta);
    }

    @Test
    void deveLancarExcecaoQuandoConsultaNaoEncontrada() {
        Integer idConsulta = 99;
        LocalDateTime dataConsulta = LocalDateTime.now();

        when(consultaGateway.getConsultaById(idConsulta)).thenReturn(Optional.empty());

        ConsultaNaoEncontradaException exception = assertThrows(
                ConsultaNaoEncontradaException.class,
                () -> useCase.execute(idConsulta, dataConsulta)
        );

        assertTrue(exception.getMessage().contains(idConsulta.toString()));
        verify(consultaGateway).getConsultaById(idConsulta);
        verify(consultaGateway, never()).deleteConsulta(anyInt());
    }
}
