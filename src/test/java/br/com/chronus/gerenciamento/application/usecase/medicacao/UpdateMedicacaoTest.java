package br.com.chronus.gerenciamento.application.usecase.medicacao;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.dto.medicacao.UpdateMedicacaoRequest;
import br.com.chronus.gerenciamento.application.gateway.MedicacaoGateway;
import br.com.chronus.gerenciamento.application.usecase.medicacao.exception.MedicacaoNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateMedicacaoTest {

    private MedicacaoGateway gateway;
    private UpdateMedicacao updateMedicacao;

    @BeforeEach
    void setup() {
        gateway = mock(MedicacaoGateway.class);
        updateMedicacao = new UpdateMedicacao(gateway);
    }

    @Test
    void deveAtualizarMedicacaoQuandoEncontrada() {

        int idMedicacao = 1;
        Medicacao medicacaoExistente = new Medicacao();
        medicacaoExistente.setIdMedicacao(idMedicacao);
        medicacaoExistente.setNomeMedicacao("Dipirona");
        medicacaoExistente.setDescricaoMedicacao("Analgésico");
        medicacaoExistente.setSigtapMedicacao("123");

        UpdateMedicacaoRequest request = new UpdateMedicacaoRequest();
        request.setNomeMedicacao("Paracetamol");
        request.setDescricaoMedicacao("Antipirético");
        request.setSigtapMedicacao("456");

        when(gateway.findMedicacaoById(idMedicacao)).thenReturn(Optional.of(medicacaoExistente));
        when(gateway.update(any(Medicacao.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Medicacao resultado = updateMedicacao.execute(idMedicacao, request);

        assertNotNull(resultado);
        assertEquals(idMedicacao, resultado.getIdMedicacao());
        assertEquals("Paracetamol", resultado.getNomeMedicacao());
        assertEquals("Antipirético", resultado.getDescricaoMedicacao());
        assertEquals("456", resultado.getSigtapMedicacao());

        verify(gateway).findMedicacaoById(idMedicacao);
        verify(gateway).update(medicacaoExistente);
    }

    @Test
    void deveLancarExcecaoQuandoMedicacaoNaoEncontrada() {

        int idMedicacao = 1;
        UpdateMedicacaoRequest request = new UpdateMedicacaoRequest();

        when(gateway.findMedicacaoById(idMedicacao)).thenReturn(Optional.empty());

        MedicacaoNaoEncontradaException exception = assertThrows(
                MedicacaoNaoEncontradaException.class,
                () -> updateMedicacao.execute(idMedicacao, request)
        );

        assertEquals("Medicação with id [1] not found.", exception.getMessage());
        assertEquals("not_found", exception.getErrorCode());

        verify(gateway).findMedicacaoById(idMedicacao);
        verify(gateway, never()).update(any());
    }
}
