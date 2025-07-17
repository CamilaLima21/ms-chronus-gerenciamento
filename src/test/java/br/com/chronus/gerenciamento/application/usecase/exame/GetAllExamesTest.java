package br.com.chronus.gerenciamento.application.usecase.exame;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class GetAllExamesTest {

    @Mock
    private ExameGateway exameGateway;

    @InjectMocks
    private GetAllExames getAllExames;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarListaDeExames() {
        List<Exame> examesMock = List.of(
                Exame.builder().idExame(1).build(),
                Exame.builder().idExame(2).build()
        );

        when(exameGateway.findAll()).thenReturn(examesMock);

        List<Exame> exames = getAllExames.execute();

        assertNotNull(exames);
        assertEquals(2, exames.size());
        assertEquals(1, exames.get(0).getIdExame());
        assertEquals(2, exames.get(1).getIdExame());

        verify(exameGateway, times(1)).findAll();
    }
}
