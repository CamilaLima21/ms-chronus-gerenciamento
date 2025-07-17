package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.EnfermidadeEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.EnfermidadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnfermidadeGatewayImplTest {

    @Mock
    private EnfermidadeRepository repository;

    @InjectMocks
    private EnfermidadeGatewayImpl gateway;

    private Enfermidade enfermidade;
    private EnfermidadeEntity entity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        enfermidade = Enfermidade.builder()
                .idEnfermidade(1)
                .enfermidade(EnumEnfermidade.DIABETES)
                .descricaoEnfermidade("Doença crônica")
                .cid("E11")
                .build();

        entity = EnfermidadeEntity.builder()
                .idEnfermidade(1)
                .enfermidade(EnumEnfermidade.DIABETES)
                .descricaoEnfermidade("Doença crônica")
                .cid("E11")
                .build();
    }

    @Test
    void testSave() {
        when(repository.save(any())).thenReturn(entity);

        Enfermidade saved = gateway.save(enfermidade);

        assertNotNull(saved);
        assertEquals(enfermidade.getCid(), saved.getCid());
        verify(repository).save(any());
    }

    @Test
    void testFindById_Success() {
        when(repository.findById(1)).thenReturn(Optional.of(entity));

        Optional<Enfermidade> result = gateway.findEnfermidadeById(1);

        assertTrue(result.isPresent());
        assertEquals(enfermidade.getCid(), result.get().getCid());
    }

    @Test
    void testUpdate_Success() {
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(repository.save(any())).thenReturn(entity);

        Enfermidade updated = gateway.update(enfermidade);

        assertNotNull(updated);
        assertEquals(enfermidade.getDescricaoEnfermidade(), updated.getDescricaoEnfermidade());
        verify(repository).save(any());
    }

    @Test
    void testUpdate_NotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                gateway.update(enfermidade));

        assertEquals("Enfermidade não encontrada com o ID [1]", exception.getMessage());
    }

    @Test
    void testDelete_Success() {
        when(repository.findById(1)).thenReturn(Optional.of(entity));

        assertDoesNotThrow(() -> gateway.delete(1));

        verify(repository).delete(entity);
    }

    @Test
    void testDelete_NotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                gateway.delete(1));

        assertEquals("Enfermidade não encontrada com o ID [1]", exception.getMessage());
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(entity));

        List<Enfermidade> result = gateway.findAll();

        assertEquals(1, result.size());
        assertEquals(enfermidade.getCid(), result.get(0).getCid());
    }

    @Test
    void testFindByCid() {
        when(repository.findByCid("E11")).thenReturn(Optional.of(entity));

        Optional<Enfermidade> result = gateway.findEnfermidadeByCid("E11");

        assertTrue(result.isPresent());
        assertEquals("E11", result.get().getCid());
    }

    @Test
    void testFindByEnumEnfermidade() {
        when(repository.findByEnfermidade(EnumEnfermidade.DIABETES)).thenReturn(Optional.of(entity));

        Optional<Enfermidade> result = gateway.findEnfermidadeByEnumEnfermidade(EnumEnfermidade.DIABETES);

        assertTrue(result.isPresent());
        assertEquals(EnumEnfermidade.DIABETES, result.get().getEnfermidade());
    }
}
