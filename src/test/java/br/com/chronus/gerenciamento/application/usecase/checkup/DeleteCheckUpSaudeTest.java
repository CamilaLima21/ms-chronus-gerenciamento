package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.checkup.exception.CheckUpSaudeNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteCheckUpSaudeTest {

    private CheckUpSaudeGateway gateway;
    private DeleteCheckUpSaude deleteCheckUpSaude;

    @BeforeEach
    void setUp() {
        gateway = Mockito.mock(CheckUpSaudeGateway.class);
        deleteCheckUpSaude = new DeleteCheckUpSaude(gateway);
    }

    @Test
    void deveDeletarCheckUpComSucessoQuandoEncontrado() {

        Integer id = 1;
        CheckUpSaude mockCheckUp = new CheckUpSaude();
        when(gateway.findById(id)).thenReturn(Optional.of(mockCheckUp));

        deleteCheckUpSaude.execute(id);

        verify(gateway, times(1)).delete(id);
    }

    @Test
    void deveLancarExcecaoQuandoCheckUpNaoForEncontrado() {

        Integer id = 99;
        when(gateway.findById(id)).thenReturn(Optional.empty());

        CheckUpSaudeNaoEncontradoException exception = assertThrows(
                CheckUpSaudeNaoEncontradoException.class,
                () -> deleteCheckUpSaude.execute(id)
        );

        assertEquals("Check-up de saúde com id [99] não encontrado.", exception.getMessage());
        assertEquals("not_found", exception.getErrorCode());

        verify(gateway, never()).delete(any());
    }
}
