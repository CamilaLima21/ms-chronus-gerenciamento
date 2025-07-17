package br.com.chronus.gerenciamento.application.usecase.medicacao;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.gateway.MedicacaoGateway;
import br.com.chronus.gerenciamento.application.usecase.medicacao.exception.MedicacaoExistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateMedicacaoTest {

    private MedicacaoGateway gateway;
    private CreateMedicacao createMedicacao;

    @BeforeEach
    void setup() {
        gateway = mock(MedicacaoGateway.class);
        createMedicacao = new CreateMedicacao(gateway);
    }

    @Test
    void deveCriarNovaMedicacaoQuandoNaoExistir() {

        Medicacao request = new Medicacao();
        request.setIdMedicacao(1);
        request.setNomeMedicacao("Dipirona");
        request.setDescricaoMedicacao("Analgésico");
        request.setSigtapMedicacao("123456");

        when(gateway.findMedicacaoById(1)).thenReturn(Optional.empty());

        when(gateway.save(any(Medicacao.class))).thenAnswer(invocation -> {
            Medicacao med = invocation.getArgument(0);
            med.setIdMedicacao(100);
            return med;
        });


        Medicacao resultado = createMedicacao.execute(request);

        assertNotNull(resultado);
        assertEquals("Dipirona", resultado.getNomeMedicacao());
        assertEquals("Analgésico", resultado.getDescricaoMedicacao());
        assertEquals("123456", resultado.getSigtapMedicacao());
        assertEquals(100, resultado.getIdMedicacao()); // id gerado
        verify(gateway).findMedicacaoById(1);
        verify(gateway).save(any(Medicacao.class));
    }

    @Test
    void deveLancarExcecaoQuandoMedicacaoJaExiste() {

        Medicacao request = new Medicacao();
        request.setIdMedicacao(1);
        request.setNomeMedicacao("Dipirona");

        when(gateway.findMedicacaoById(1)).thenReturn(Optional.of(request));

        MedicacaoExistenteException exception = assertThrows(
                MedicacaoExistenteException.class,
                () -> createMedicacao.execute(request)
        );

        assertEquals("Medicação [Dipirona] with id [1] already exists.", exception.getMessage());
        assertEquals("already_exists", exception.getErrorCode());
        verify(gateway).findMedicacaoById(1);
        verify(gateway, never()).save(any());
    }
}
