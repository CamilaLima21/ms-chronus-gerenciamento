package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.checkup.exception.CheckUpSaudeNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindCheckUpSaudeByIdTest {

    private CheckUpSaudeGateway gateway;
    private FindCheckUpSaudeById useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(CheckUpSaudeGateway.class);
        useCase = new FindCheckUpSaudeById(gateway);
    }

    @Test
    void deveRetornarCheckUpQuandoEncontrado() {

        Integer id = 1;
        CheckUpSaude esperado = new CheckUpSaude();
        when(gateway.findById(id)).thenReturn(Optional.of(esperado));

        CheckUpSaude resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(esperado, resultado);
        verify(gateway).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoCheckUpNaoForEncontrado() {

        Integer id = 99;
        when(gateway.findById(id)).thenReturn(Optional.empty());

        CheckUpSaudeNaoEncontradoException ex = assertThrows(
                CheckUpSaudeNaoEncontradoException.class,
                () -> useCase.execute(id)
        );

        assertEquals("Check-up de saúde com id [99] não encontrado.", ex.getMessage());
        verify(gateway).findById(id);
    }
}
