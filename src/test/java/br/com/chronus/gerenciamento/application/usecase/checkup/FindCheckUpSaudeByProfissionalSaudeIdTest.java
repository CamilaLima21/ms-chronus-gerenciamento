package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FindCheckUpSaudeByProfissionalSaudeIdTest {

    private CheckUpSaudeGateway checkUpGateway;
    private ProfissionalSaudeGateway profissionalGateway;
    private FindCheckUpSaudeByProfissionalSaudeId useCase;

    @BeforeEach
    void setUp() {
        checkUpGateway = mock(CheckUpSaudeGateway.class);
        profissionalGateway = mock(ProfissionalSaudeGateway.class);
        useCase = new FindCheckUpSaudeByProfissionalSaudeId(checkUpGateway, profissionalGateway);
    }

    @Test
    void deveRetornarCheckUpsQuandoProfissionalExiste() {

        Integer idProfissional = 100;
        List<CheckUpSaude> checkUps = List.of(new CheckUpSaude(), new CheckUpSaude());

        when(profissionalGateway.verificaProfissionalPorId(idProfissional)).thenReturn(true);
        when(checkUpGateway.findByProfissionalSaudeId(idProfissional)).thenReturn(checkUps);

        List<CheckUpSaude> resultado = useCase.execute(idProfissional);

        assertEquals(2, resultado.size());
        verify(profissionalGateway).verificaProfissionalPorId(idProfissional);
        verify(checkUpGateway).findByProfissionalSaudeId(idProfissional);
    }

    @Test
    void deveLancarExcecaoQuandoProfissionalNaoExiste() {

        Integer idProfissional = 404;

        when(profissionalGateway.verificaProfissionalPorId(idProfissional)).thenReturn(false);

        ProfissionalSaudeNaoEncontradoException exception = assertThrows(
                ProfissionalSaudeNaoEncontradoException.class,
                () -> useCase.execute(idProfissional)
        );

        assertEquals("Profissional de saúde com ID 404 não encontrado.", exception.getMessage());
        verify(profissionalGateway).verificaProfissionalPorId(idProfissional);
        verify(checkUpGateway, never()).findByProfissionalSaudeId(any());
    }
}
