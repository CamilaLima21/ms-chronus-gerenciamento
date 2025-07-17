package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.dto.medicacao.UpdateMedicacaoRequest;
import br.com.chronus.gerenciamento.application.gateway.MedicacaoGateway;
import br.com.chronus.gerenciamento.application.mapper.MedicacaoMapper;
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

class MedicacaoControllerTest {

    @InjectMocks
    private MedicacaoController controller;

    @Mock
    private MedicacaoGateway medicacaoGateway;

    @Mock
    private MedicacaoMapper medicacaoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMedicacao() {
        UpdateMedicacaoRequest dto = new UpdateMedicacaoRequest();
        Medicacao domain = new Medicacao();
        Medicacao saved = new Medicacao();

        when(medicacaoMapper.toDomain(dto, null)).thenReturn(domain);
        when(medicacaoGateway.save(domain)).thenReturn(saved);

        ResponseEntity<Medicacao> response = controller.createMedicacao(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(saved, response.getBody());

        verify(medicacaoMapper).toDomain(dto, null);
        verify(medicacaoGateway).save(domain);
    }

    @Test
    void testGetMedicacaoById_Found() {
        int id = 1;
        Medicacao medicacao = new Medicacao();

        when(medicacaoGateway.findMedicacaoById(id)).thenReturn(Optional.of(medicacao));

        ResponseEntity<Medicacao> response = controller.getMedicacaoById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicacao, response.getBody());

        verify(medicacaoGateway).findMedicacaoById(id);
    }

    @Test
    void testGetMedicacaoById_NotFound() {
        int id = 1;

        when(medicacaoGateway.findMedicacaoById(id)).thenReturn(Optional.empty());

        ResponseEntity<Medicacao> response = controller.getMedicacaoById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(medicacaoGateway).findMedicacaoById(id);
    }

    @Test
    void testUpdateMedicacao() {
        int id = 1;
        UpdateMedicacaoRequest dto = new UpdateMedicacaoRequest();
        Medicacao domain = new Medicacao();
        Medicacao updated = new Medicacao();

        when(medicacaoMapper.toDomain(dto, id)).thenReturn(domain);
        when(medicacaoGateway.update(domain)).thenReturn(updated);

        ResponseEntity<Medicacao> response = controller.updateMedicacao(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updated, response.getBody());

        verify(medicacaoMapper).toDomain(dto, id);
        verify(medicacaoGateway).update(domain);
    }

    @Test
    void testDeleteMedicacao() {
        int id = 1;

        doNothing().when(medicacaoGateway).delete(id);

        ResponseEntity<Void> response = controller.deleteMedicacao(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(medicacaoGateway).delete(id);
    }

    @Test
    void testGetAllMedicacoes() {
        List<Medicacao> list = Arrays.asList(new Medicacao(), new Medicacao());

        when(medicacaoGateway.findAll()).thenReturn(list);

        ResponseEntity<List<Medicacao>> response = controller.getAllMedicacoes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());

        verify(medicacaoGateway).findAll();
    }

    @Test
    void testGetMedicacaoBySigtap_Found() {
        String sigtap = "12345";
        Medicacao medicacao = new Medicacao();

        when(medicacaoGateway.findMedicacaoBySigtap(sigtap)).thenReturn(Optional.of(medicacao));

        ResponseEntity<Medicacao> response = controller.getMedicacaoBySigtap(sigtap);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicacao, response.getBody());

        verify(medicacaoGateway).findMedicacaoBySigtap(sigtap);
    }

    @Test
    void testGetMedicacaoBySigtap_NotFound() {
        String sigtap = "12345";

        when(medicacaoGateway.findMedicacaoBySigtap(sigtap)).thenReturn(Optional.empty());

        ResponseEntity<Medicacao> response = controller.getMedicacaoBySigtap(sigtap);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(medicacaoGateway).findMedicacaoBySigtap(sigtap);
    }

    @Test
    void testGetMedicacaoByNome_Found() {
        String nome = "Medicamento X";
        Medicacao medicacao = new Medicacao();

        when(medicacaoGateway.findMedicacaoByNome(nome)).thenReturn(Optional.of(medicacao));

        ResponseEntity<Medicacao> response = controller.getMedicacaoByNome(nome);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicacao, response.getBody());

        verify(medicacaoGateway).findMedicacaoByNome(nome);
    }

    @Test
    void testGetMedicacaoByNome_NotFound() {
        String nome = "Medicamento X";

        when(medicacaoGateway.findMedicacaoByNome(nome)).thenReturn(Optional.empty());

        ResponseEntity<Medicacao> response = controller.getMedicacaoByNome(nome);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(medicacaoGateway).findMedicacaoByNome(nome);
    }
}
