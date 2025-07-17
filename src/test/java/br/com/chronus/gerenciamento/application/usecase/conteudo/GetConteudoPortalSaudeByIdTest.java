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

class GetConteudoPortalSaudeByIdTest {

    private ConteudoPortalSaudeGateway gateway;
    private GetConteudoPortalSaudeById getConteudoById;

    @BeforeEach
    void setUp() {
        gateway = mock(ConteudoPortalSaudeGateway.class);
        getConteudoById = new GetConteudoPortalSaudeById(gateway);
    }

    @Test
    void deveRetornarConteudoQuandoEncontrado() {

        int id = 1;
        ConteudoPortalSaude conteudo = ConteudoPortalSaude.builder()
                .id(id)
                .filtroPortalSaude(EnumFiltroPortalSaude.CUIDADOS_ASMA)
                .conteudos(List.of("Conteúdo 1", "Conteúdo 2"))
                .build();

        when(gateway.findById(id)).thenReturn(Optional.of(conteudo));

        ConteudoPortalSaude resultado = getConteudoById.execute(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(EnumFiltroPortalSaude.CUIDADOS_ASMA, resultado.getFiltroPortalSaude());
        assertEquals(List.of("Conteúdo 1", "Conteúdo 2"), resultado.getConteudos());

        verify(gateway, times(1)).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoConteudoNaoEncontrado() {

        int id = 999;
        when(gateway.findById(id)).thenReturn(Optional.empty());

        ConteudoNaoEncontradoException exception = assertThrows(ConteudoNaoEncontradoException.class, () -> {
            getConteudoById.execute(id);
        });

        assertEquals("Conteúdo com id [999] não encontrado.", exception.getMessage());

        verify(gateway, times(1)).findById(id);
    }
}
