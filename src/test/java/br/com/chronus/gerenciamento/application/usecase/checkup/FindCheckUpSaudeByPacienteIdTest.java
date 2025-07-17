package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FindCheckUpSaudeByPacienteIdTest {

    private CheckUpSaudeGateway checkUpGateway;
    private PacienteGateway pacienteGateway;
    private FindCheckUpSaudeByPacienteId useCase;

    @BeforeEach
    void setUp() {
        checkUpGateway = mock(CheckUpSaudeGateway.class);
        pacienteGateway = mock(PacienteGateway.class);
        useCase = new FindCheckUpSaudeByPacienteId(checkUpGateway, pacienteGateway);
    }

    @Test
    void deveRetornarListaDeCheckUpsQuandoPacienteExiste() {

        Integer idPaciente = 123;
        List<CheckUpSaude> listaEsperada = List.of(new CheckUpSaude(), new CheckUpSaude());

        when(pacienteGateway.verificaPacientePorId(idPaciente)).thenReturn(true);
        when(checkUpGateway.findByPacienteId(idPaciente)).thenReturn(listaEsperada);

        List<CheckUpSaude> resultado = useCase.execute(idPaciente);

        assertEquals(listaEsperada.size(), resultado.size());
        verify(pacienteGateway).verificaPacientePorId(idPaciente);
        verify(checkUpGateway).findByPacienteId(idPaciente);
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoExiste() {

        Integer idPaciente = 456;
        when(pacienteGateway.verificaPacientePorId(idPaciente)).thenReturn(false);

        PacienteNaoEncontradoException ex = assertThrows(
                PacienteNaoEncontradoException.class,
                () -> useCase.execute(idPaciente)
        );

        assertEquals("Paciente com ID 456 não encontrado.", ex.getMessage());
        verify(pacienteGateway).verificaPacientePorId(idPaciente);
        verify(checkUpGateway, never()).findByPacienteId(any());
    }
}
