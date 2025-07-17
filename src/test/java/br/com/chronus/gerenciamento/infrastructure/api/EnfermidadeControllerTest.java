package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.dto.enfermidades.EnfermidadeRequest;
import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import br.com.chronus.gerenciamento.application.gateway.EnfermidadeGateway;
import br.com.chronus.gerenciamento.application.mapper.EnfermidadeMapper;
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

class EnfermidadeControllerTest {

    @InjectMocks
    private EnfermidadeController controller;

    @Mock
    private EnfermidadeGateway enfermidadeGateway;

    @Mock
    private EnfermidadeMapper enfermidadeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEnfermidade() {
        EnfermidadeRequest dto = new EnfermidadeRequest();
        Enfermidade domain = new Enfermidade();
        Enfermidade saved = new Enfermidade();

        when(enfermidadeMapper.mapToDomain(dto, null)).thenReturn(domain);
        when(enfermidadeGateway.save(domain)).thenReturn(saved);

        ResponseEntity<Enfermidade> response = controller.createEnfermidade(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(saved, response.getBody());

        verify(enfermidadeMapper).mapToDomain(dto, null);
        verify(enfermidadeGateway).save(domain);
    }

    @Test
    void testGetEnfermidadeById_Found() {
        int id = 1;
        Enfermidade enfermidade = new Enfermidade();

        when(enfermidadeGateway.findEnfermidadeById(id)).thenReturn(Optional.of(enfermidade));

        ResponseEntity<Enfermidade> response = controller.getEnfermidadeById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(enfermidade, response.getBody());

        verify(enfermidadeGateway).findEnfermidadeById(id);
    }

    @Test
    void testGetEnfermidadeById_NotFound() {
        int id = 1;

        when(enfermidadeGateway.findEnfermidadeById(id)).thenReturn(Optional.empty());

        ResponseEntity<Enfermidade> response = controller.getEnfermidadeById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(enfermidadeGateway).findEnfermidadeById(id);
    }

    @Test
    void testUpdateEnfermidade() {
        int id = 1;
        EnfermidadeRequest dto = new EnfermidadeRequest();
        Enfermidade domain = new Enfermidade();
        Enfermidade updated = new Enfermidade();

        when(enfermidadeMapper.mapToDomain(dto, id)).thenReturn(domain);
        when(enfermidadeGateway.update(domain)).thenReturn(updated);

        ResponseEntity<Enfermidade> response = controller.updateEnfermidade(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updated, response.getBody());

        verify(enfermidadeMapper).mapToDomain(dto, id);
        verify(enfermidadeGateway).update(domain);
    }

    @Test
    void testDeleteEnfermidade() {
        int id = 1;

        doNothing().when(enfermidadeGateway).delete(id);

        ResponseEntity<Void> response = controller.deleteEnfermidade(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(enfermidadeGateway).delete(id);
    }

    @Test
    void testGetAllEnfermidades() {
        List<Enfermidade> list = Arrays.asList(new Enfermidade(), new Enfermidade());

        when(enfermidadeGateway.findAll()).thenReturn(list);

        ResponseEntity<List<Enfermidade>> response = controller.getAllEnfermidades();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());

        verify(enfermidadeGateway).findAll();
    }

    @Test
    void testGetEnfermidadeByCid_Found() {
        String cid = "A00";
        Enfermidade enfermidade = new Enfermidade();

        when(enfermidadeGateway.findEnfermidadeByCid(cid)).thenReturn(Optional.of(enfermidade));

        ResponseEntity<Enfermidade> response = controller.getEnfermidadeByCid(cid);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(enfermidade, response.getBody());

        verify(enfermidadeGateway).findEnfermidadeByCid(cid);
    }

    @Test
    void testGetEnfermidadeByCid_NotFound() {
        String cid = "A00";

        when(enfermidadeGateway.findEnfermidadeByCid(cid)).thenReturn(Optional.empty());

        ResponseEntity<Enfermidade> response = controller.getEnfermidadeByCid(cid);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(enfermidadeGateway).findEnfermidadeByCid(cid);
    }

    @Test
    void testGetEnfermidadeByEnum_Found() {
        EnumEnfermidade enumEnf = EnumEnfermidade.ASMA;
        Enfermidade enfermidade = new Enfermidade();

        when(enfermidadeGateway.findEnfermidadeByEnumEnfermidade(enumEnf)).thenReturn(Optional.of(enfermidade));

        ResponseEntity<Enfermidade> response = controller.getEnfermidadeByEnum(enumEnf);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(enfermidade, response.getBody());

        verify(enfermidadeGateway).findEnfermidadeByEnumEnfermidade(enumEnf);
    }

    @Test
    void testGetEnfermidadeByEnum_NotFound() {
        EnumEnfermidade enumEnf = EnumEnfermidade.ASMA;

        when(enfermidadeGateway.findEnfermidadeByEnumEnfermidade(enumEnf)).thenReturn(Optional.empty());

        ResponseEntity<Enfermidade> response = controller.getEnfermidadeByEnum(enumEnf);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(enfermidadeGateway).findEnfermidadeByEnumEnfermidade(enumEnf);
    }

}
