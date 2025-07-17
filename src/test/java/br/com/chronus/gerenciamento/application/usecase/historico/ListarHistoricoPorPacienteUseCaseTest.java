package br.com.chronus.gerenciamento.application.usecase.historico;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListarHistoricoPorPacienteUseCaseTest {

    private HistoricoGateway historicoGateway;
    private PacienteGateway pacienteGateway;
    private ListarHistoricoPorPacienteUseCase useCase;

    @BeforeEach
    void setUp() {
        historicoGateway = mock(HistoricoGateway.class);
        pacienteGateway = mock(PacienteGateway.class);
        useCase = new ListarHistoricoPorPacienteUseCase(historicoGateway, pacienteGateway);
    }

    @Test
    void deveRetornarListaDeHistoricoQuandoPacienteExiste() {

        Integer idPaciente = 1;
        Historico historico = Historico.builder()
                .id(100)
                .idPaciente(idPaciente)
                .enfermidades(Collections.emptyList())
                .tratamentos(Collections.emptyList())
                .consultas(Collections.emptyList())
                .checkups(Collections.emptyList())
                .observacoes("Paciente em acompanhamento.")
                .dataInicio(LocalDate.of(2023, 1, 1))
                .dataFim(LocalDate.of(2023, 12, 31))
                .build();

        when(pacienteGateway.verificaPacientePorId(idPaciente)).thenReturn(true);
        when(historicoGateway.listarPorPaciente(idPaciente)).thenReturn(List.of(historico));

        List<Historico> resultado = useCase.executar(idPaciente);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(historico.getId(), resultado.get(0).getId());
        assertEquals(historico.getObservacoes(), resultado.get(0).getObservacoes());

        verify(pacienteGateway, times(1)).verificaPacientePorId(idPaciente);
        verify(historicoGateway, times(1)).listarPorPaciente(idPaciente);
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoExiste() {

        Integer idPaciente = 999;
        when(pacienteGateway.verificaPacientePorId(idPaciente)).thenReturn(false);

        assertThrows(PacienteNaoEncontradoException.class, () -> useCase.executar(idPaciente));

        verify(pacienteGateway, times(1)).verificaPacientePorId(idPaciente);
        verify(historicoGateway, never()).listarPorPaciente(any());
    }
}
