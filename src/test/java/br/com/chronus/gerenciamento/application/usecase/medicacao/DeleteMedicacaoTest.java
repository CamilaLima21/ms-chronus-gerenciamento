package br.com.chronus.gerenciamento.application.usecase.medicacao;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.gateway.MedicacaoGateway;
import br.com.chronus.gerenciamento.application.usecase.medicacao.exception.MedicacaoNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteMedicacaoTest {

    private MedicacaoGateway gateway;
    private DeleteMedicacao deleteMedicacao;

    @BeforeEach
    void setup() {
        gateway = mock(MedicacaoGateway.class);
        deleteMedicacao = new DeleteMedicacao(gateway);
    }

    @Test
    void deveExcluirMedicacaoQuandoEncontrada() {

        Medicacao medicacao = new Medicacao();
        medicacao.setIdMedicacao(1);
        when(gateway.findMedicacaoById(1)).thenReturn(Optional.of(medicacao));

        deleteMedicacao.execute(1);

        verify(gateway).findMedicacaoById(1);
        verify(gateway).delete(1);
    }

    @Test
    void deveLancarExcecaoQuandoMedicacaoNaoEncontrada() {

        when(gateway.findMedicacaoById(1)).thenReturn(Optional.empty());

        MedicacaoNaoEncontradaException exception = assertThrows(
                MedicacaoNaoEncontradaException.class,
                () -> deleteMedicacao.execute(1)
        );

        assertEquals("Medicação with id [1] not found.", exception.getMessage());
        assertEquals("not_found", exception.getErrorCode());
        verify(gateway).findMedicacaoById(1);
        verify(gateway, never()).delete(anyInt());
    }
}
