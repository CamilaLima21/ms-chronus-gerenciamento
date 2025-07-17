package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.mapper.CheckUpMapper;
import br.com.chronus.gerenciamento.application.mapper.ConsultaMapper;
import br.com.chronus.gerenciamento.application.mapper.EnfermidadeMapper;
import br.com.chronus.gerenciamento.application.mapper.TratamentoMapper;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.HistoricoEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.HistoricoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HistoricoGatewayImplTest {

    @Mock
    private HistoricoRepository historicoRepository;

    @Mock
    private EnfermidadeMapper enfermidadeMapper;

    @Mock
    private TratamentoMapper tratamentoMapper;

    @Mock
    private ConsultaMapper consultaMapper;

    @Mock
    private CheckUpMapper checkupMapper;

    @InjectMocks
    private HistoricoGatewayImpl historicoGateway;

    private Historico domain;
    private HistoricoEntity entity;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        domain = new Historico();
        domain.setId(1);
        domain.setIdPaciente(10);
        domain.setObservacoes("Observações teste");
        domain.setDataInicio(LocalDate.now().minusDays(10));
        domain.setDataFim(LocalDate.now());

        entity = new HistoricoEntity();
        entity.setId(1L);
        entity.setIdPaciente(10);
        entity.setObservacoes("Observações teste");
        entity.setDataInicio(domain.getDataInicio());
        entity.setDataFim(domain.getDataFim());

        when(enfermidadeMapper.toEnfermidadeEntity(any())).thenReturn(null);
        when(tratamentoMapper.mapToEntity(any())).thenReturn(null);
        when(consultaMapper.toConsultaEntity(any())).thenReturn(null);
        when(checkupMapper.mapToEntity(any())).thenReturn(null);

        when(enfermidadeMapper.toEnfermidade(any())).thenReturn(null);
        when(tratamentoMapper.mapToDomain(any())).thenReturn(null);
        when(consultaMapper.toConsulta(any())).thenReturn(null);
        when(checkupMapper.mapToDomain(any())).thenReturn(null);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testSalvar() {
        when(historicoRepository.save(any())).thenReturn(entity);

        Historico saved = historicoGateway.salvar(domain);

        assertNotNull(saved);
        assertEquals(domain.getId(), saved.getId());
        verify(historicoRepository).save(any());
    }

    @Test
    void testBuscarPorIdFound() {
        when(historicoRepository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<Historico> result = historicoGateway.buscarPorId(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    void testBuscarPorIdNotFound() {
        when(historicoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Historico> result = historicoGateway.buscarPorId(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void testListarPorPaciente() {
        when(historicoRepository.findByIdPaciente(10L)).thenReturn(List.of(entity));

        List<Historico> result = historicoGateway.listarPorPaciente(10);

        assertEquals(1, result.size());
        assertEquals(10, result.get(0).getIdPaciente());
    }

    @Test
    void testDeletarPorId() {
        doNothing().when(historicoRepository).deleteById(1L);

        assertDoesNotThrow(() -> historicoGateway.deletarPorId(1));

        verify(historicoRepository).deleteById(1L);
    }
}
