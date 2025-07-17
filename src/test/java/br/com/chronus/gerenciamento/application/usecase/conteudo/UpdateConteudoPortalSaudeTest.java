package br.com.chronus.gerenciamento.application.usecase.conteudo;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.conteudo.exception.ConteudoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateConteudoPortalSaudeTest {

    private ConteudoPortalSaudeGateway gateway;
    private UpdateConteudoPortalSaude updateConteudo;

    @BeforeEach
    void setUp() {
        gateway = mock(ConteudoPortalSaudeGateway.class);
        updateConteudo = new UpdateConteudoPortalSaude(gateway);
    }

    @Test
    void deveAtualizarConteudoQuandoExistir() {

        ConteudoPortalSaude conteudoParaAtualizar = ConteudoPortalSaude.builder()
                .id(1)
                .filtroPortalSaude(EnumFiltroPortalSaude.PREVENCAO_CANCERES)
                .conteudos(List.of("Novo conteúdo"))
                .build();

        when(gateway.findById(1)).thenReturn(Optional.of(conteudoParaAtualizar));
        when(gateway.update(conteudoParaAtualizar)).thenReturn(conteudoParaAtualizar);

        ConteudoPortalSaude resultado = updateConteudo.execute(conteudoParaAtualizar);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals(EnumFiltroPortalSaude.PREVENCAO_CANCERES, resultado.getFiltroPortalSaude());
        assertEquals(List.of("Novo conteúdo"), resultado.getConteudos());

        verify(gateway, times(1)).findById(1);
        verify(gateway, times(1)).update(conteudoParaAtualizar);
    }

    @Test
    void deveLancarExcecaoQuandoConteudoNaoExistir() {

        ConteudoPortalSaude conteudoParaAtualizar = ConteudoPortalSaude.builder()
                .id(99)
                .filtroPortalSaude(EnumFiltroPortalSaude.PREVENCAO_CANCERES)
                .conteudos(List.of("Conteúdo qualquer"))
                .build();

        when(gateway.findById(99)).thenReturn(Optional.empty());

        ConteudoNaoEncontradoException exception = assertThrows(ConteudoNaoEncontradoException.class, () -> {
            updateConteudo.execute(conteudoParaAtualizar);
        });

        assertEquals("Conteúdo com id [99] não encontrado.", exception.getMessage());

        verify(gateway, times(1)).findById(99);
        verify(gateway, never()).update(any());
    }
}
