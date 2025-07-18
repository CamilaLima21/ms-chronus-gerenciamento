package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import br.com.chronus.gerenciamento.application.mapper.TratamentoMapper;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.MedicacaoEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.TratamentoEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.TratamentoRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TratamentoGatewayImplTest {

    @Mock
    private TratamentoRepository tratamentoRepository;

    @Mock
    private TratamentoMapper tratamentoMapper;

    @InjectMocks
    private TratamentoGatewayImpl tratamentoGateway;

    private AutoCloseable closeable;

    private Tratamento tratamentoDomain;
    private TratamentoEntity tratamentoEntity;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        MedicacaoEntity medicamentoEntity = MedicacaoEntity.builder()
                .idMedicacao(100)
                .nomeMedicacao("Medicamento Teste")
                .build();

        List<MedicacaoEntity> medicamentosEntity = List.of(medicamentoEntity);

        tratamentoEntity = TratamentoEntity.builder()
                .idTratamento(1)
                .medicamentos(medicamentosEntity)
                .inicioTratamento(LocalDate.of(2023, 1, 1))
                .fimTratamento(LocalDate.of(2023, 1, 31))
                .periodicidade("Diária")
                .dosagem("2x ao dia")
                .horarios(List.of(HorarioEnum.H08, HorarioEnum.H20))
                .build();

        Medicacao medicamentoDomain = Medicacao.builder()
                .idMedicacao(100)
                .nomeMedicacao("Medicamento Teste")
                .build();

        tratamentoDomain = Tratamento.builder()
                .idTratamento(1)
                .medicamentos(List.of(medicamentoDomain))
                .inicioTratamento(LocalDate.of(2023, 1, 1))
                .fimTratamento(LocalDate.of(2023, 1, 31))
                .periodicidade("Diária")
                .dosagem("2x ao dia")
                .horarios(List.of(HorarioEnum.H08, HorarioEnum.H20))
                .build();
    }

    @Test
    void testSave() {
        when(tratamentoMapper.mapToEntity(tratamentoDomain)).thenReturn(tratamentoEntity);
        when(tratamentoRepository.save(tratamentoEntity)).thenReturn(tratamentoEntity);
        when(tratamentoMapper.mapToDomain(tratamentoEntity)).thenReturn(tratamentoDomain);

        Tratamento saved = tratamentoGateway.save(tratamentoDomain);

        assertNotNull(saved);
        assertEquals(tratamentoDomain.getIdTratamento(), saved.getIdTratamento());
        assertEquals("Diária", saved.getPeriodicidade());
        assertEquals(2, saved.getHorarios().size());
        verify(tratamentoRepository).save(tratamentoEntity);
    }

    @Test
    void testFindByIdFound() {
        when(tratamentoRepository.findById(1)).thenReturn(Optional.of(tratamentoEntity));
        when(tratamentoMapper.mapToDomain(tratamentoEntity)).thenReturn(tratamentoDomain);

        Optional<Tratamento> result = tratamentoGateway.findById(1);

        assertTrue(result.isPresent());
        assertEquals(tratamentoDomain.getIdTratamento(), result.get().getIdTratamento());
        assertEquals("Diária", result.get().getPeriodicidade());
    }

    @Test
    void testFindByIdNotFound() {
        when(tratamentoRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Tratamento> result = tratamentoGateway.findById(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void testUpdateSuccess() {
        when(tratamentoRepository.findById(1)).thenReturn(Optional.of(tratamentoEntity));
        when(tratamentoMapper.mapToEntity(tratamentoDomain)).thenReturn(tratamentoEntity);
        when(tratamentoRepository.save(tratamentoEntity)).thenReturn(tratamentoEntity);
        when(tratamentoMapper.mapToDomain(tratamentoEntity)).thenReturn(tratamentoDomain);

        Tratamento updated = tratamentoGateway.update(tratamentoDomain);

        assertNotNull(updated);
        assertEquals(tratamentoDomain.getIdTratamento(), updated.getIdTratamento());
    }

    @Test
    void testUpdateNotFound() {
        when(tratamentoRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> tratamentoGateway.update(tratamentoDomain));

        assertEquals("Tratamento não encontrado com o ID [1]", ex.getMessage());
    }

    @Test
    void testDeleteSuccess() {
        when(tratamentoRepository.findById(1)).thenReturn(Optional.of(tratamentoEntity));

        assertDoesNotThrow(() -> tratamentoGateway.delete(1));

        verify(tratamentoRepository).delete(tratamentoEntity);
    }

    @Test
    void testDeleteNotFound() {
        when(tratamentoRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> tratamentoGateway.delete(1));

        assertEquals("Tratamento não encontrado com o ID [1]", ex.getMessage());
    }

    @Test
    void testFindAll() {
        when(tratamentoRepository.findAll()).thenReturn(List.of(tratamentoEntity));
        when(tratamentoMapper.mapToDomain(tratamentoEntity)).thenReturn(tratamentoDomain);

        List<Tratamento> result = tratamentoGateway.findAll();

        assertEquals(1, result.size());
        assertEquals(tratamentoDomain.getIdTratamento(), result.get(0).getIdTratamento());
    }

    @Test
    void testFindByPacienteId() {
        Integer idPaciente = 123;

        when(tratamentoRepository.findByIdPaciente(idPaciente)).thenReturn(List.of(tratamentoEntity));
        when(tratamentoMapper.mapToDomain(tratamentoEntity)).thenReturn(tratamentoDomain);

        List<Tratamento> result = tratamentoGateway.findByPacienteId(idPaciente);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(tratamentoDomain.getIdTratamento(), result.get(0).getIdTratamento());

        verify(tratamentoRepository).findByIdPaciente(idPaciente);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
}
