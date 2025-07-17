package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ExameEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.ExameRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExameGatewayImplTest {

    @Mock
    private ExameRepository exameRepository;

    @InjectMocks
    private ExameGatewayImpl exameGateway;

    private Exame exame;
    private ExameEntity entity;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        exame = Exame.builder()
                .idExame(1)
                .idPaciente(10)
                .idProfissionalSaude(20)
                .dataExame(LocalDateTime.of(2023, 1, 1, 10, 0))
                .listaExames(List.of(EnumExame.HEMOGLOBINA_GLICADA_DIABETES, EnumExame.GLICEMIA_JEJUM_DIABETES))
                .statusExame(EnumStatusExame.AGENDADO)
                .build();

        entity = ExameEntity.builder()
                .idExame(1)
                .idPaciente(10)
                .idProfissionalSaude(20)
                .dataExame(LocalDateTime.of(2023, 1, 1, 10, 0))
                .listaExames(List.of(EnumExame.HEMOGLOBINA_GLICADA_DIABETES, EnumExame.GLICEMIA_JEJUM_DIABETES))
                .statusExame(EnumStatusExame.AGENDADO)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testSave() {
        when(exameRepository.save(any())).thenReturn(entity);

        Exame saved = exameGateway.save(exame);

        assertNotNull(saved);
        assertEquals(1, saved.getIdExame());
        verify(exameRepository).save(any());
    }

    @Test
    void testFindByIdFound() {
        when(exameRepository.findById(1)).thenReturn(Optional.of(entity));

        Optional<Exame> result = exameGateway.findExameById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdExame());
    }

    @Test
    void testFindByIdNotFound() {
        when(exameRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Exame> result = exameGateway.findExameById(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void testUpdateSuccess() {
        when(exameRepository.findById(1)).thenReturn(Optional.of(entity));
        when(exameRepository.save(any())).thenReturn(entity);

        Exame updated = exameGateway.update(exame);

        assertNotNull(updated);
        assertEquals(EnumStatusExame.AGENDADO, updated.getStatusExame());
    }

    @Test
    void testUpdateNotFound() {
        when(exameRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> exameGateway.update(exame));

        assertEquals("Exame não encontrado com o ID [1]", ex.getMessage());
    }

    @Test
    void testDeleteSuccess() {
        when(exameRepository.findById(1)).thenReturn(Optional.of(entity));

        assertDoesNotThrow(() -> exameGateway.delete(1));

        verify(exameRepository).delete(entity);
    }

    @Test
    void testDeleteNotFound() {
        when(exameRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> exameGateway.delete(1));

        assertEquals("Exame não encontrado com o ID [1]", ex.getMessage());
    }

    @Test
    void testFindAll() {
        when(exameRepository.findAll()).thenReturn(List.of(entity));

        List<Exame> result = exameGateway.findAll();

        assertEquals(1, result.size());
        assertEquals(10, result.get(0).getIdPaciente());
    }

    @Test
    void testFindExamesByPaciente() {
        when(exameRepository.findByIdPaciente(10)).thenReturn(List.of(entity));

        List<Exame> result = exameGateway.findExamesByPaciente(10);

        assertEquals(1, result.size());
        assertEquals(10, result.get(0).getIdPaciente());
    }

    @Test
    void testFindExamesByProfissional() {
        when(exameRepository.findAll()).thenReturn(List.of(entity));

        List<Exame> result = exameGateway.findExamesByProfissionalSaude(20);

        assertEquals(1, result.size());
        assertEquals(20, result.get(0).getIdProfissionalSaude());
    }

    @Test
    void testFindExamesByStatus() {
        when(exameRepository.findByStatusExame(EnumStatusExame.AGENDADO)).thenReturn(List.of(entity));

        List<Exame> result = exameGateway.findExamesByStatus(EnumStatusExame.AGENDADO);

        assertEquals(1, result.size());
        assertEquals(EnumStatusExame.AGENDADO, result.get(0).getStatusExame());
    }

    @Test
    void testFindExamesByTipo() {
        when(exameRepository.findAll()).thenReturn(List.of(entity));

        List<Exame> result = exameGateway.findExamesByTipo(EnumExame.HEMOGLOBINA_GLICADA_DIABETES);

        assertEquals(1, result.size());
        assertTrue(result.get(0).getListaExames().contains(EnumExame.HEMOGLOBINA_GLICADA_DIABETES));
    }
}
