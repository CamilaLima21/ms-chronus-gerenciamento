package br.com.chronus.gerenciamento.application.usecase.exame;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import br.com.chronus.gerenciamento.application.usecase.exame.exception.ExameNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteExameTest {

    @Mock
    private ExameGateway exameGateway;

    @InjectMocks
    private DeleteExame deleteExame;

    private final int idExameExistente = 1;
    private final int idExameNaoExistente = 99;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveDeletarExameQuandoExistir() {
        Exame exame = Exame.builder().idExame(idExameExistente).build();
        when(exameGateway.findExameById(idExameExistente)).thenReturn(Optional.of(exame));

        deleteExame.execute(idExameExistente);

        verify(exameGateway).delete(idExameExistente);
    }

    @Test
    void deveLancarExcecaoQuandoExameNaoExistir() {
        when(exameGateway.findExameById(idExameNaoExistente)).thenReturn(Optional.empty());

        ExameNaoEncontradoException exception = assertThrows(ExameNaoEncontradoException.class,
                () -> deleteExame.execute(idExameNaoExistente));

        assertEquals("Exame com id [99] não encontrado.", exception.getMessage());
        verify(exameGateway, never()).delete(anyInt());
    }
}
