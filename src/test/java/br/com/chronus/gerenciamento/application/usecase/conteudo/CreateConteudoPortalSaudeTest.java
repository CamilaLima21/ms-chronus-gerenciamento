package br.com.chronus.gerenciamento.application.usecase.conteudo;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.conteudo.exception.ConteudoExistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class CreateConteudoPortalSaudeTest {

    private ConteudoPortalSaudeGateway gateway;
    private CreateConteudoPortalSaude createConteudo;

    @BeforeEach
    void setup() {
        gateway = mock(ConteudoPortalSaudeGateway.class);
        createConteudo = new CreateConteudoPortalSaude(gateway);
    }

    @Test
    void execute_deveSalvarQuandoConteudoNaoExistir() {

        ConteudoPortalSaude novoConteudo = new ConteudoPortalSaude();
        novoConteudo.setId(1);

        when(gateway.findById(1)).thenReturn(Optional.empty());
        when(gateway.save(novoConteudo)).thenReturn(novoConteudo);

        ConteudoPortalSaude resultado = createConteudo.execute(novoConteudo);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1);

        verify(gateway, times(1)).findById(1);
        verify(gateway, times(1)).save(novoConteudo);
    }

    @Test
    void execute_deveLancarExcecaoQuandoConteudoExistir() {

        ConteudoPortalSaude conteudoExistente = new ConteudoPortalSaude();
        conteudoExistente.setId(2);

        when(gateway.findById(2)).thenReturn(Optional.of(conteudoExistente));

        ConteudoPortalSaude novoConteudo = new ConteudoPortalSaude();
        novoConteudo.setId(2);

        assertThatThrownBy(() -> createConteudo.execute(novoConteudo))
                .isInstanceOf(ConteudoExistenteException.class)
                .hasMessageContaining("2");

        verify(gateway, times(1)).findById(2);
        verify(gateway, never()).save(any());
    }
}
