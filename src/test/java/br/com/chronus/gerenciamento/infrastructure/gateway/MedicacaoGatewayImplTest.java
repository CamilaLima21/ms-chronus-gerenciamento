package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.MedicacaoEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.MedicacaoRepository;
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

class MedicacaoGatewayImplTest {

    @Mock
    private MedicacaoRepository medicacaoRepository;

    @InjectMocks
    private MedicacaoGatewayImpl medicacaoGateway;

    private Medicacao medicacao;
    private MedicacaoEntity entity;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        medicacao = Medicacao.builder()
                .idMedicacao(1)
                .nomeMedicacao("Medicamento A")
                .descricaoMedicacao("Descrição do Medicamento A")
                .sigtapMedicacao("12345")
                .build();

        entity = MedicacaoEntity.builder()
                .idMedicacao(1)
                .nomeMedicacao("Medicamento A")
                .descricaoMedicacao("Descrição do Medicamento A")
                .sigtapMedicacao("12345")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testSave() {
        when(medicacaoRepository.save(any())).thenReturn(entity);

        Medicacao saved = medicacaoGateway.save(medicacao);

        assertNotNull(saved);
        assertEquals(1, saved.getIdMedicacao());
        verify(medicacaoRepository).save(any());
    }

    @Test
    void testFindByIdFound() {
        when(medicacaoRepository.findById(1)).thenReturn(Optional.of(entity));

        Optional<Medicacao> result = medicacaoGateway.findMedicacaoById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdMedicacao());
    }

    @Test
    void testFindByIdNotFound() {
        when(medicacaoRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Medicacao> result = medicacaoGateway.findMedicacaoById(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void testUpdateSuccess() {
        when(medicacaoRepository.findById(1)).thenReturn(Optional.of(entity));
        when(medicacaoRepository.save(any())).thenReturn(entity);

        Medicacao updated = medicacaoGateway.update(medicacao);

        assertNotNull(updated);
        assertEquals("Medicamento A", updated.getNomeMedicacao());
    }

    @Test
    void testUpdateNotFound() {
        when(medicacaoRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> medicacaoGateway.update(medicacao));

        assertEquals("Medicação não encontrada com o ID [1]", ex.getMessage());
    }

    @Test
    void testDeleteSuccess() {
        when(medicacaoRepository.findById(1)).thenReturn(Optional.of(entity));

        assertDoesNotThrow(() -> medicacaoGateway.delete(1));

        verify(medicacaoRepository).delete(entity);
    }

    @Test
    void testDeleteNotFound() {
        when(medicacaoRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> medicacaoGateway.delete(1));

        assertEquals("Medicação não encontrada com o ID [1]", ex.getMessage());
    }

    @Test
    void testFindAll() {
        when(medicacaoRepository.findAll()).thenReturn(List.of(entity));

        List<Medicacao> result = medicacaoGateway.findAll();

        assertEquals(1, result.size());
        assertEquals("Medicamento A", result.get(0).getNomeMedicacao());
    }

    @Test
    void testFindBySigtap() {
        when(medicacaoRepository.findBySigtapMedicacao("12345")).thenReturn(Optional.of(entity));

        Optional<Medicacao> result = medicacaoGateway.findMedicacaoBySigtap("12345");

        assertTrue(result.isPresent());
        assertEquals("12345", result.get().getSigtapMedicacao());
    }

    @Test
    void testFindByNome() {
        when(medicacaoRepository.findByNomeMedicacao("Medicamento A")).thenReturn(Optional.of(entity));

        Optional<Medicacao> result = medicacaoGateway.findMedicacaoByNome("Medicamento A");

        assertTrue(result.isPresent());
        assertEquals("Medicamento A", result.get().getNomeMedicacao());
    }
}
