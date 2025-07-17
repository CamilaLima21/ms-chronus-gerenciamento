package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
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

class TratamentoControllerTest {

    @InjectMocks
    private TratamentoController controller;

    @Mock
    private TratamentoGateway tratamentoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTratamento() {
        Tratamento tratamento = new Tratamento();
        Tratamento saved = new Tratamento();

        when(tratamentoGateway.save(tratamento)).thenReturn(saved);

        ResponseEntity<Tratamento> response = controller.createTratamento(tratamento);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(saved, response.getBody());

        verify(tratamentoGateway).save(tratamento);
    }

    @Test
    void testGetTratamentoById_Found() {
        int id = 1;
        Tratamento tratamento = new Tratamento();

        when(tratamentoGateway.findById(id)).thenReturn(Optional.of(tratamento));

        ResponseEntity<Tratamento> response = controller.getTratamentoById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tratamento, response.getBody());

        verify(tratamentoGateway).findById(id);
    }

    @Test
    void testGetTratamentoById_NotFound() {
        int id = 1;

        when(tratamentoGateway.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Tratamento> response = controller.getTratamentoById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(tratamentoGateway).findById(id);
    }

    @Test
    void testUpdateTratamento() {
        int id = 1;
        Tratamento tratamento = new Tratamento();
        Tratamento updated = new Tratamento();

        when(tratamentoGateway.update(tratamento)).thenReturn(updated);

        ResponseEntity<Tratamento> response = controller.updateTratamento(id, tratamento);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updated, response.getBody());
        assertEquals(id, tratamento.getIdTratamento());

        verify(tratamentoGateway).update(tratamento);
    }

    @Test
    void testDeleteTratamento() {
        int id = 1;

        doNothing().when(tratamentoGateway).delete(id);

        ResponseEntity<Void> response = controller.deleteTratamento(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(tratamentoGateway).delete(id);
    }

    @Test
    void testGetAllTratamentos() {
        List<Tratamento> list = Arrays.asList(new Tratamento(), new Tratamento());

        when(tratamentoGateway.findAll()).thenReturn(list);

        ResponseEntity<List<Tratamento>> response = controller.getAllTratamentos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());

        verify(tratamentoGateway).findAll();
    }
}
