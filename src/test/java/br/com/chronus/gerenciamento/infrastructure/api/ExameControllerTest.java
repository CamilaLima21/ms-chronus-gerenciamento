package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.dto.exame.ExameRequest;
import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import br.com.chronus.gerenciamento.application.mapper.ExameMapper;
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

class ExameControllerTest {

    @InjectMocks
    private ExameController controller;

    @Mock
    private ExameGateway exameGateway;

    @Mock
    private ExameMapper exameMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateExame() {
        ExameRequest dto = new ExameRequest();
        Exame domain = new Exame();
        Exame saved = new Exame();

        when(exameMapper.toDomain(dto, null)).thenReturn(domain);
        when(exameGateway.save(domain)).thenReturn(saved);

        ResponseEntity<Exame> response = controller.createExame(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(saved, response.getBody());

        verify(exameMapper).toDomain(dto, null);
        verify(exameGateway).save(domain);
    }

    @Test
    void testGetExameById_Found() {
        int id = 1;
        Exame exame = new Exame();

        when(exameGateway.findExameById(id)).thenReturn(Optional.of(exame));

        ResponseEntity<Exame> response = controller.getExameById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exame, response.getBody());

        verify(exameGateway).findExameById(id);
    }

    @Test
    void testGetExameById_NotFound() {
        int id = 1;

        when(exameGateway.findExameById(id)).thenReturn(Optional.empty());

        ResponseEntity<Exame> response = controller.getExameById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(exameGateway).findExameById(id);
    }

    @Test
    void testUpdateExame() {
        int id = 1;
        ExameRequest dto = new ExameRequest();
        Exame domain = new Exame();
        Exame updated = new Exame();

        when(exameMapper.toDomain(dto, id)).thenReturn(domain);
        when(exameGateway.update(domain)).thenReturn(updated);

        ResponseEntity<Exame> response = controller.updateExame(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updated, response.getBody());

        verify(exameMapper).toDomain(dto, id);
        verify(exameGateway).update(domain);
    }

    @Test
    void testDeleteExame() {
        int id = 1;

        doNothing().when(exameGateway).delete(id);

        ResponseEntity<Void> response = controller.deleteExame(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(exameGateway).delete(id);
    }

    @Test
    void testGetAllExames() {
        List<Exame> list = Arrays.asList(new Exame(), new Exame());

        when(exameGateway.findAll()).thenReturn(list);

        ResponseEntity<List<Exame>> response = controller.getAllExames();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());

        verify(exameGateway).findAll();
    }

    @Test
    void testGetExamesByPaciente() {
        int idPaciente = 10;
        List<Exame> exames = Arrays.asList(new Exame());

        when(exameGateway.findExamesByPaciente(idPaciente)).thenReturn(exames);

        ResponseEntity<List<Exame>> response = controller.getExamesByPaciente(idPaciente);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exames, response.getBody());

        verify(exameGateway).findExamesByPaciente(idPaciente);
    }

    @Test
    void testGetExamesByProfissional() {
        int idProfissional = 20;
        List<Exame> exames = Arrays.asList(new Exame());

        when(exameGateway.findExamesByProfissionalSaude(idProfissional)).thenReturn(exames);

        ResponseEntity<List<Exame>> response = controller.getExamesByProfissional(idProfissional);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exames, response.getBody());

        verify(exameGateway).findExamesByProfissionalSaude(idProfissional);
    }

    @Test
    void testGetExamesByStatus() {
        EnumStatusExame status = EnumStatusExame.AGENDADO;
        List<Exame> exames = Arrays.asList(new Exame());

        when(exameGateway.findExamesByStatus(status)).thenReturn(exames);

        ResponseEntity<List<Exame>> response = controller.getExamesByStatus(status);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exames, response.getBody());

        verify(exameGateway).findExamesByStatus(status);
    }

    @Test
    void testGetExamesByTipo() {
        EnumExame tipo = EnumExame.ESPIROMETRIA_ASMA; // valor válido do enum
        List<Exame> exames = Arrays.asList(new Exame());

        when(exameGateway.findExamesByTipo(tipo)).thenReturn(exames);

        ResponseEntity<List<Exame>> response = controller.getExamesByTipo(tipo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exames, response.getBody());

        verify(exameGateway).findExamesByTipo(tipo);
    }
}
