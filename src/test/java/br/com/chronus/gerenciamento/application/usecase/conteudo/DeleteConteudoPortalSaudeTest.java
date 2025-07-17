package br.com.chronus.gerenciamento.application.usecase.conteudo;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.conteudo.exception.ConteudoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteConteudoPortalSaudeTest {

    private ConteudoPortalSaudeGateway gateway;
    private DeleteConteudoPortalSaude deleteConteudo;

    @BeforeEach
    void setUp() {
        gateway = mock(ConteudoPortalSaudeGateway.class);
        deleteConteudo = new DeleteConteudoPortalSaude(gateway);
    }

    @Test
    void deveDeletarConteudoQuandoExistir() {
        // Arrange
        int id = 1;
        ConteudoPortalSaude conteudo = ConteudoPortalSaude.builder().id(id).build();
        when(gateway.findById(id)).thenReturn(Optional.of(conteudo));

        // Act
        deleteConteudo.execute(id);

        // Assert
        verify(gateway, times(1)).findById(id);
        verify(gateway, times(1)).delete(id);
    }

    @Test
    void deveLancarExcecaoQuandoConteudoNaoExistir() {
        // Arrange
        int id = 999;
        when(gateway.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        ConteudoNaoEncontradoException exception = assertThrows(
                ConteudoNaoEncontradoException.class,
                () -> deleteConteudo.execute(id)
        );

        assertEquals("Conteúdo com id [999] não encontrado.", exception.getMessage());
        assertEquals("not_found", exception.getErrorCode());

        verify(gateway, times(1)).findById(id);
        verify(gateway, never()).delete(anyInt());
    }
}
