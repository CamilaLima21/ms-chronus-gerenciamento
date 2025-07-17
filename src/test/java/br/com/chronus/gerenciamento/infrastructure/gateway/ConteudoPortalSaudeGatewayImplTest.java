package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ConteudoPortalSaudeEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.ConteudoPortalSaudeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConteudoPortalSaudeGatewayImplTest {

    @InjectMocks
    private ConteudoPortalSaudeGatewayImpl gateway;

    @Mock
    private ConteudoPortalSaudeRepository repository;

    private AutoCloseable closeable;

    private ConteudoPortalSaude domain;
    private ConteudoPortalSaudeEntity entity;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        domain = ConteudoPortalSaude.builder()
                .id(1)
                .filtroPortalSaude(EnumFiltroPortalSaude.ALIMENTACAO_COLESTEROL)
                .conteudos(List.of("Dica 1", "Dica 2"))
                .build();

        entity = ConteudoPortalSaudeEntity.builder()
                .id(1)
                .filtroPortalSaude(EnumFiltroPortalSaude.ALIMENTACAO_COLESTEROL)
                .conteudos(List.of("Dica 1", "Dica 2"))
                .build();
    }


    @Test
    void testSave() {
        when(repository.save(any(ConteudoPortalSaudeEntity.class))).thenReturn(entity);

        ConteudoPortalSaude saved = gateway.save(domain);

        assertEquals(domain.getId(), saved.getId());
        assertEquals(domain.getFiltroPortalSaude(), saved.getFiltroPortalSaude());
        assertEquals(domain.getConteudos(), saved.getConteudos());
        verify(repository).save(any(ConteudoPortalSaudeEntity.class));
    }

    @Test
    void testFindById_found() {
        when(repository.findById(1)).thenReturn(Optional.of(entity));

        Optional<ConteudoPortalSaude> result = gateway.findById(1);

        assertTrue(result.isPresent());
        assertEquals(domain.getId(), result.get().getId());
    }

    @Test
    void testFindById_notFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        Optional<ConteudoPortalSaude> result = gateway.findById(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void testUpdate_success() {
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(repository.save(any(ConteudoPortalSaudeEntity.class))).thenReturn(entity);

        ConteudoPortalSaude updated = gateway.update(domain);

        assertEquals(domain.getId(), updated.getId());
        verify(repository).save(any(ConteudoPortalSaudeEntity.class));
    }

    @Test
    void testUpdate_notFound_throwsException() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gateway.update(domain);
        });

        assertEquals("Conteúdo não encontrado com o ID [1]", exception.getMessage());
    }

    @Test
    void testDelete_success() {
        when(repository.findById(1)).thenReturn(Optional.of(entity));

        gateway.delete(1);

        verify(repository).delete(entity);
    }

    @Test
    void testDelete_notFound_throwsException() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gateway.delete(1);
        });

        assertEquals("Conteúdo não encontrado com o ID [1]", exception.getMessage());
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(entity));

        List<ConteudoPortalSaude> result = gateway.findAll();

        assertEquals(1, result.size());
        assertEquals(domain.getId(), result.get(0).getId());
    }

    @Test
    void testFindByFiltro() {
        when(repository.findByFiltroPortalSaude(EnumFiltroPortalSaude.ALIMENTACAO_COLESTEROL))
                .thenReturn(List.of(entity));

        List<ConteudoPortalSaude> result = gateway.findByFiltro(EnumFiltroPortalSaude.ALIMENTACAO_COLESTEROL);

        assertEquals(1, result.size());
        assertEquals(EnumFiltroPortalSaude.ALIMENTACAO_COLESTEROL, result.get(0).getFiltroPortalSaude());
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

}
