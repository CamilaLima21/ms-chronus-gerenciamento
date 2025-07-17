package br.com.chronus.gerenciamento.application.usecase.conteudo;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetAllConteudosPortalSaudeTest {

    private ConteudoPortalSaudeGateway gateway;
    private GetAllConteudosPortalSaude getAllConteudos;

    @BeforeEach
    void setUp() {
        gateway = mock(ConteudoPortalSaudeGateway.class);
        getAllConteudos = new GetAllConteudosPortalSaude(gateway);
    }

    @Test
    void deveRetornarListaDeConteudosComFiltrosEEnfermidades() {
        // Arrange
        List<ConteudoPortalSaude> conteudosMock = List.of(
                ConteudoPortalSaude.builder()
                        .id(1)
                        .filtroPortalSaude(EnumFiltroPortalSaude.CUIDADOS_ASMA)
                        .conteudos(List.of("Dicas para asma", "Exercícios respiratórios"))
                        .build(),
                ConteudoPortalSaude.builder()
                        .id(2)
                        .filtroPortalSaude(EnumFiltroPortalSaude.PREVENCAO_CANCERES)
                        .conteudos(List.of("Prevenção de câncer de mama", "Importância do exame"))
                        .build()
        );
        when(gateway.findAll()).thenReturn(conteudosMock);

        // Act
        List<ConteudoPortalSaude> resultado = getAllConteudos.execute();

        // Assert
        assertEquals(2, resultado.size());

        // Verificar primeiro conteúdo
        ConteudoPortalSaude primeiro = resultado.get(0);
        assertEquals(1, primeiro.getId());
        assertEquals(EnumFiltroPortalSaude.CUIDADOS_ASMA, primeiro.getFiltroPortalSaude());
        assertEquals(EnumEnfermidade.ASMA, primeiro.getFiltroPortalSaude().getEnfermidade());
        assertEquals(List.of("Dicas para asma", "Exercícios respiratórios"), primeiro.getConteudos());

        // Verificar segundo conteúdo
        ConteudoPortalSaude segundo = resultado.get(1);
        assertEquals(2, segundo.getId());
        assertEquals(EnumFiltroPortalSaude.PREVENCAO_CANCERES, segundo.getFiltroPortalSaude());
        assertEquals(EnumEnfermidade.CANCERES, segundo.getFiltroPortalSaude().getEnfermidade());
        assertEquals(List.of("Prevenção de câncer de mama", "Importância do exame"), segundo.getConteudos());

        verify(gateway, times(1)).findAll();
    }
}
