package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.mapper.CheckUpMapper;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.CheckUpSaudeEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.CheckUpSaudeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckUpSaudeGatewayImplTest {

    @InjectMocks
    private CheckUpSaudeGatewayImpl gateway;

    @Mock
    private CheckUpSaudeRepository repository;

    @Mock
    private CheckUpMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        CheckUpSaude domain = new CheckUpSaude();
        CheckUpSaudeEntity entity = new CheckUpSaudeEntity();
        CheckUpSaudeEntity savedEntity = new CheckUpSaudeEntity();
        CheckUpSaude savedDomain = new CheckUpSaude();

        when(mapper.mapToEntity(domain)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.mapToDomain(savedEntity)).thenReturn(savedDomain);

        CheckUpSaude result = gateway.save(domain);

        assertEquals(savedDomain, result);
        verify(mapper).mapToEntity(domain);
        verify(repository).save(entity);
        verify(mapper).mapToDomain(savedEntity);
    }

    @Test
    void testFindById_Found() {
        Integer id = 1;
        CheckUpSaudeEntity entity = new CheckUpSaudeEntity();
        CheckUpSaude domain = new CheckUpSaude();

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.mapToDomain(entity)).thenReturn(domain);

        Optional<CheckUpSaude> result = gateway.findById(id);

        assertTrue(result.isPresent());
        assertEquals(domain, result.get());
        verify(repository).findById(id);
        verify(mapper).mapToDomain(entity);
    }

    @Test
    void testFindById_NotFound() {
        Integer id = 99;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<CheckUpSaude> result = gateway.findById(id);

        assertFalse(result.isPresent());
        verify(repository).findById(id);
        verifyNoInteractions(mapper);
    }

    @Test
    void testUpdate_Success() {
        CheckUpSaude domain = new CheckUpSaude();
        domain.setIdCheckUpsaude(10);
        CheckUpSaudeEntity existingEntity = new CheckUpSaudeEntity();
        existingEntity.setIdCheckUpsaude(10);
        CheckUpSaudeEntity toSave = new CheckUpSaudeEntity();
        CheckUpSaudeEntity updatedEntity = new CheckUpSaudeEntity();
        CheckUpSaude updatedDomain = new CheckUpSaude();

        when(repository.findById(10)).thenReturn(Optional.of(existingEntity));
        when(mapper.mapToEntity(domain)).thenReturn(toSave);
        when(repository.save(toSave)).thenReturn(updatedEntity);
        when(mapper.mapToDomain(updatedEntity)).thenReturn(updatedDomain);

        CheckUpSaude result = gateway.update(domain);

        assertEquals(updatedDomain, result);
        verify(repository).findById(10);
        verify(repository).save(toSave);
        verify(mapper).mapToEntity(domain);
        verify(mapper).mapToDomain(updatedEntity);
    }

    @Test
    void testUpdate_NotFound() {
        CheckUpSaude domain = new CheckUpSaude();
        domain.setIdCheckUpsaude(99);

        when(repository.findById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                gateway.update(domain));

        assertTrue(exception.getMessage().contains("Check-up de saúde não encontrado"));
        verify(repository).findById(99);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    void testDelete_Success() {
        Integer id = 1;
        CheckUpSaudeEntity entity = new CheckUpSaudeEntity();
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        gateway.delete(id);

        verify(repository).findById(id);
        verify(repository).delete(entity);
    }

    @Test
    void testDelete_NotFound() {
        Integer id = 999;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                gateway.delete(id));

        assertTrue(exception.getMessage().contains("Check-up de saúde não encontrado"));
        verify(repository).findById(id);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testFindAll() {
        List<CheckUpSaudeEntity> entities = Arrays.asList(new CheckUpSaudeEntity(), new CheckUpSaudeEntity());
        List<CheckUpSaude> domains = Arrays.asList(new CheckUpSaude(), new CheckUpSaude());

        when(repository.findAll()).thenReturn(entities);
        when(mapper.mapToDomain(any())).thenReturn(domains.get(0), domains.get(1));

        List<CheckUpSaude> result = gateway.findAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
        verify(mapper, times(2)).mapToDomain(any());
    }

    @Test
    void testFindByPacienteId() {
        Integer pacienteId = 42;
        List<CheckUpSaudeEntity> entities = Arrays.asList(new CheckUpSaudeEntity());
        List<CheckUpSaude> domains = Arrays.asList(new CheckUpSaude());

        when(repository.findByIdPaciente(pacienteId)).thenReturn(entities);
        when(mapper.mapToDomain(any())).thenReturn(domains.get(0));

        List<CheckUpSaude> result = gateway.findByPacienteId(pacienteId);

        assertEquals(1, result.size());
        verify(repository).findByIdPaciente(pacienteId);
        verify(mapper).mapToDomain(any());
    }

    @Test
    void testFindByProfissionalSaudeId() {
        Integer profissionalId = 99;
        List<CheckUpSaudeEntity> entities = Arrays.asList(new CheckUpSaudeEntity());
        List<CheckUpSaude> domains = Arrays.asList(new CheckUpSaude());

        when(repository.findByIdProfissionalSaude(profissionalId)).thenReturn(entities);
        when(mapper.mapToDomain(any())).thenReturn(domains.get(0));

        List<CheckUpSaude> result = gateway.findByProfissionalSaudeId(profissionalId);

        assertEquals(1, result.size());
        verify(repository).findByIdProfissionalSaude(profissionalId);
        verify(mapper).mapToDomain(any());
    }
}
