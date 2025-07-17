package br.com.chronus.gerenciamento.application.usecase.historico;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SalvarHistoricoUseCaseTest {

    private HistoricoGateway historicoGateway;
    private PacienteGateway pacienteGateway;
    private SalvarHistoricoUseCase salvarHistoricoUseCase;

    @BeforeEach
    void setUp() {
        historicoGateway = mock(HistoricoGateway.class);
        pacienteGateway = mock(PacienteGateway.class);
        salvarHistoricoUseCase = new SalvarHistoricoUseCase(historicoGateway, pacienteGateway);
    }

    @Test
    void deveSalvarHistoricoComPacienteValido() {

        Historico historico = Historico.builder()
                .id(1)
                .idPaciente(100)
                .enfermidades(Collections.emptyList())
                .tratamentos(Collections.emptyList())
                .consultas(Collections.emptyList())
                .checkups(Collections.emptyList())
                .observacoes("Paciente com histórico estável.")
                .dataInicio(LocalDate.now().minusMonths(3))
                .dataFim(LocalDate.now())
                .build();

        when(pacienteGateway.verificaPacientePorId(100)).thenReturn(true);
        when(historicoGateway.salvar(historico)).thenReturn(historico);

        Historico resultado = salvarHistoricoUseCase.executar(historico);

        assertNotNull(resultado);
        assertEquals(historico, resultado);
        verify(pacienteGateway, times(1)).verificaPacientePorId(100);
        verify(historicoGateway, times(1)).salvar(historico);
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoExiste() {

        Historico historico = Historico.builder()
                .idPaciente(200)
                .build();

        when(pacienteGateway.verificaPacientePorId(200)).thenReturn(false);

        assertThrows(PacienteNaoEncontradoException.class, () -> salvarHistoricoUseCase.executar(historico));
        verify(pacienteGateway).verificaPacientePorId(200);
        verify(historicoGateway, never()).salvar(any());
    }
}
