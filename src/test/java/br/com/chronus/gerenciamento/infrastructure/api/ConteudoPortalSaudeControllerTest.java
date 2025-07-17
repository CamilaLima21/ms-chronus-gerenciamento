package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.dto.portal.ConteudoPortalSaudeRequest;
import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import br.com.chronus.gerenciamento.application.mapper.ConteudoPortalSaudeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ConteudoPortalSaudeControllerTest {

    @InjectMocks
    private ConteudoPortalSaudeController controller;

    @Mock
    private ConteudoPortalSaudeGateway gateway;

    @Mock
    private ConteudoPortalSaudeMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateConteudo() {
        ConteudoPortalSaudeRequest dto = new ConteudoPortalSaudeRequest();
        ConteudoPortalSaude domain = new ConteudoPortalSaude();
        ConteudoPortalSaude saved = new ConteudoPortalSaude();

        when(mapper.toDomain(dto, null)).thenReturn(domain);
        when(gateway.save(domain)).thenReturn(saved);

        ResponseEntity<ConteudoPortalSaude> response = controller.createConteudo(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(saved, response.getBody());

        verify(mapper).toDomain(dto, null);
        verify(gateway).save(domain);
    }

    @Test
    void testGetConteudoById_Found() {
        int id = 1;
        ConteudoPortalSaude conteudo = new ConteudoPortalSaude();

        when(gateway.findById(id)).thenReturn(Optional.of(conteudo));

        ResponseEntity<ConteudoPortalSaude> response = controller.getConteudoById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(conteudo, response.getBody());

        verify(gateway).findById(id);
    }

    @Test
    void testGetConteudoById_NotFound() {
        int id = 1;

        when(gateway.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<ConteudoPortalSaude> response = controller.getConteudoById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(gateway).findById(id);
    }

    @Test
    void testUpdateConteudo() {
        int id = 1;
        ConteudoPortalSaudeRequest dto = new ConteudoPortalSaudeRequest();
        ConteudoPortalSaude domain = new ConteudoPortalSaude();
        ConteudoPortalSaude updated = new ConteudoPortalSaude();

        when(mapper.toDomain(dto, id)).thenReturn(domain);
        when(gateway.update(domain)).thenReturn(updated);

        ResponseEntity<ConteudoPortalSaude> response = controller.updateConteudo(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updated, response.getBody());

        verify(mapper).toDomain(dto, id);
        verify(gateway).update(domain);
    }

    @Test
    void testDeleteConteudo() {
        int id = 1;

        doNothing().when(gateway).delete(id);

        ResponseEntity<Void> response = controller.deleteConteudo(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(gateway).delete(id);
    }

    @Test
    void testGetAllConteudos() {
        List<ConteudoPortalSaude> list = Arrays.asList(new ConteudoPortalSaude(), new ConteudoPortalSaude());

        when(gateway.findAll()).thenReturn(list);

        ResponseEntity<List<ConteudoPortalSaude>> response = controller.getAllConteudos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());

        verify(gateway).findAll();
    }

    @Test
    void testGetConteudosByFiltro() {
        EnumFiltroPortalSaude filtro = EnumFiltroPortalSaude.CUIDADOS_ASMA; // Use um valor válido do enum
        List<ConteudoPortalSaude> list = Arrays.asList(new ConteudoPortalSaude());

        when(gateway.findByFiltro(filtro)).thenReturn(list);

        ResponseEntity<List<ConteudoPortalSaude>> response = controller.getConteudosByFiltro(filtro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());

        verify(gateway).findByFiltro(filtro);
    }

}
