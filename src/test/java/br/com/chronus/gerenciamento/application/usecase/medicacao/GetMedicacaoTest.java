package br.com.chronus.gerenciamento.application.usecase.medicacao;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.gateway.MedicacaoGateway;
import br.com.chronus.gerenciamento.application.usecase.medicacao.exception.MedicacaoNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetMedicacaoTest {

    private MedicacaoGateway gateway;
    private GetMedicacao getMedicacao;

    @BeforeEach
    void setup() {
        gateway = mock(MedicacaoGateway.class);
        getMedicacao = new GetMedicacao(gateway);
    }

    @Test
    void deveRetornarMedicacaoQuandoEncontrada() {

        Medicacao medicacao = new Medicacao();
        medicacao.setIdMedicacao(1);
        medicacao.setNomeMedicacao("Dipirona");
        when(gateway.findMedicacaoById(1)).thenReturn(Optional.of(medicacao));

        Medicacao resultado = getMedicacao.execute(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdMedicacao());
        assertEquals("Dipirona", resultado.getNomeMedicacao());
        verify(gateway).findMedicacaoById(1);
    }

    @Test
    void deveLancarExcecaoQuandoMedicacaoNaoEncontrada() {

        when(gateway.findMedicacaoById(1)).thenReturn(Optional.empty());

        MedicacaoNaoEncontradaException exception = assertThrows(
                MedicacaoNaoEncontradaException.class,
                () -> getMedicacao.execute(1)
        );

        assertEquals("Medicação with id [1] not found.", exception.getMessage());
        assertEquals("not_found", exception.getErrorCode());
        verify(gateway).findMedicacaoById(1);
    }
}
