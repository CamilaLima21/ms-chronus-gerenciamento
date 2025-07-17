package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.dto.historico.HistoricoRequestDto;
import br.com.chronus.gerenciamento.application.mapper.HistoricoMapper;
import br.com.chronus.gerenciamento.application.usecase.historico.BuscarHistoricoPorIdUseCase;
import br.com.chronus.gerenciamento.application.usecase.historico.DeletarHistoricoPorIdUseCase;
import br.com.chronus.gerenciamento.application.usecase.historico.ListarHistoricoPorPacienteUseCase;
import br.com.chronus.gerenciamento.application.usecase.historico.SalvarHistoricoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class HistoricoControllerTest {

    @InjectMocks
    private HistoricoController controller;

    @Mock
    private SalvarHistoricoUseCase salvarHistoricoUseCase;

    @Mock
    private BuscarHistoricoPorIdUseCase buscarHistoricoPorIdUseCase;

    @Mock
    private ListarHistoricoPorPacienteUseCase listarHistoricoPorPacienteUseCase;

    @Mock
    private DeletarHistoricoPorIdUseCase deletarHistoricoPorIdUseCase;

    @Mock
    private HistoricoMapper historicoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvar() {
        HistoricoRequestDto dto = new HistoricoRequestDto();
        Historico domain = new Historico();
        Historico saved = new Historico();

        when(historicoMapper.toDomain(dto)).thenReturn(domain);
        when(salvarHistoricoUseCase.executar(domain)).thenReturn(saved);
        when(salvarHistoricoUseCase.executar(saved)).thenReturn(saved); // conforme controller chama duas vezes (talvez ajustar controller)

        ResponseEntity<Historico> response = controller.salvar(dto);

        assertEquals(saved, response.getBody());
        assertEquals(200, response.getStatusCodeValue());

        verify(historicoMapper).toDomain(dto);
        verify(salvarHistoricoUseCase, times(2)).executar(any());
    }

    @Test
    void testBuscarPorId_Found() {
        int id = 1;
        Historico historico = new Historico();

        when(buscarHistoricoPorIdUseCase.executar(id)).thenReturn(Optional.of(historico));

        ResponseEntity<Historico> response = controller.buscarPorId(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(historico, response.getBody());

        verify(buscarHistoricoPorIdUseCase).executar(id);
    }

    @Test
    void testBuscarPorId_NotFound() {
        int id = 1;

        when(buscarHistoricoPorIdUseCase.executar(id)).thenReturn(Optional.empty());

        ResponseEntity<Historico> response = controller.buscarPorId(id);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());

        verify(buscarHistoricoPorIdUseCase).executar(id);
    }

    @Test
    void testListarPorPaciente() {
        int idPaciente = 10;
        List<Historico> lista = Arrays.asList(new Historico(), new Historico());

        when(listarHistoricoPorPacienteUseCase.executar(idPaciente)).thenReturn(lista);

        ResponseEntity<List<Historico>> response = controller.listarPorPaciente(idPaciente);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(lista, response.getBody());

        verify(listarHistoricoPorPacienteUseCase).executar(idPaciente);
    }

    @Test
    void testDeletar() {
        int id = 1;

        doNothing().when(deletarHistoricoPorIdUseCase).executar(id);

        ResponseEntity<Void> response = controller.deletar(id);

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());

        verify(deletarHistoricoPorIdUseCase).executar(id);
    }
}
