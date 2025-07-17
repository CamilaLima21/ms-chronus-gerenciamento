package br.com.chronus.gerenciamento.application.usecase.tratamento;

import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListTratamentosUseCaseTest {

    private TratamentoGateway tratamentoGateway;
    private ListTratamentosUseCase listTratamentosUseCase;

    @BeforeEach
    void setUp() {
        tratamentoGateway = mock(TratamentoGateway.class);
        listTratamentosUseCase = new ListTratamentosUseCase(tratamentoGateway);
    }

    @Test
    void deveRetornarListaDeTratamentosQuandoExistirem() {
        List<Tratamento> tratamentos = new ArrayList<>();
        tratamentos.add(new Tratamento());
        tratamentos.add(new Tratamento());

        when(tratamentoGateway.findAll()).thenReturn(tratamentos);

        List<Tratamento> resultado = listTratamentosUseCase.execute();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(tratamentoGateway, times(1)).findAll();
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremTratamentos() {
        when(tratamentoGateway.findAll()).thenReturn(new ArrayList<>());

        List<Tratamento> resultado = listTratamentosUseCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(tratamentoGateway, times(1)).findAll();
    }
}
