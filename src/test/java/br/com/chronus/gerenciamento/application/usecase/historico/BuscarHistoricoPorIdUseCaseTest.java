package br.com.chronus.gerenciamento.application.usecase.historico;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarHistoricoPorIdUseCaseTest {

    private HistoricoGateway historicoGateway;
    private PacienteGateway pacienteGateway;
    private BuscarHistoricoPorIdUseCase useCase;

    @BeforeEach
    void setUp() {
        historicoGateway = mock(HistoricoGateway.class);
        pacienteGateway = mock(PacienteGateway.class);
        useCase = new BuscarHistoricoPorIdUseCase(historicoGateway, pacienteGateway);
    }

    @Test
    void deveRetornarHistoricoQuandoEncontradoEPacienteExiste() {

        Integer idHistorico = 1;
        Integer idPaciente = 123;
        Historico historico = new Historico();
        historico.setId(idHistorico);
        historico.setIdPaciente(idPaciente);

        when(historicoGateway.buscarPorId(idHistorico)).thenReturn(Optional.of(historico));
        when(pacienteGateway.verificaPacientePorId(idPaciente)).thenReturn(true);

        Optional<Historico> resultado = useCase.executar(idHistorico);

        assertTrue(resultado.isPresent());
        assertEquals(historico, resultado.get());
        verify(historicoGateway).buscarPorId(idHistorico);
        verify(pacienteGateway).verificaPacientePorId(idPaciente);
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoExiste() {

        Integer idHistorico = 2;
        Integer idPaciente = 456;
        Historico historico = new Historico();
        historico.setId(idHistorico);
        historico.setIdPaciente(idPaciente);

        when(historicoGateway.buscarPorId(idHistorico)).thenReturn(Optional.of(historico));
        when(pacienteGateway.verificaPacientePorId(idPaciente)).thenReturn(false);

        PacienteNaoEncontradoException ex = assertThrows(PacienteNaoEncontradoException.class,
                () -> useCase.executar(idHistorico));

        assertEquals("Paciente com ID 456 não encontrado.", ex.getMessage());
        verify(historicoGateway).buscarPorId(idHistorico);
        verify(pacienteGateway).verificaPacientePorId(idPaciente);
    }

    @Test
    void deveRetornarOptionalEmptyQuandoHistoricoNaoExiste() {

        Integer idHistorico = 3;
        when(historicoGateway.buscarPorId(idHistorico)).thenReturn(Optional.empty());

        Optional<Historico> resultado = useCase.executar(idHistorico);

        assertTrue(resultado.isEmpty());
        verify(historicoGateway).buscarPorId(idHistorico);
        verifyNoInteractions(pacienteGateway);
    }
}
