package br.com.chronus.gerenciamento.application.usecase.consulta;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ConsultaNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetConsultaTest {

    @Mock
    private ConsultaGateway consultaGateway;

    @InjectMocks
    private GetConsulta getConsulta;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarConsultaQuandoEncontrada() {
        Integer idConsulta = 1;
        Consulta consulta = Consulta.builder()
                .idConsulta(idConsulta)
                .build();

        when(consultaGateway.getConsultaById(idConsulta)).thenReturn(Optional.of(consulta));

        Consulta resultado = getConsulta.execute(idConsulta);

        assertNotNull(resultado);
        assertEquals(idConsulta, resultado.getIdConsulta());
        verify(consultaGateway, times(1)).getConsultaById(idConsulta);
    }

    @Test
    void deveLancarExcecaoQuandoConsultaNaoEncontrada() {
        Integer idConsulta = 2;

        when(consultaGateway.getConsultaById(idConsulta)).thenReturn(Optional.empty());

        ConsultaNaoEncontradaException exception = assertThrows(
                ConsultaNaoEncontradaException.class,
                () -> getConsulta.execute(idConsulta)
        );

        assertEquals("Consulta com id [2] não encontrada.", exception.getMessage());
        verify(consultaGateway, times(1)).getConsultaById(idConsulta);
    }
}
