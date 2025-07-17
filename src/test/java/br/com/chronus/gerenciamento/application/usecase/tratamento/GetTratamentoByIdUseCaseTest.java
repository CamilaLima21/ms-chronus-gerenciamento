package br.com.chronus.gerenciamento.application.usecase.tratamento;

import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GetTratamentoByIdUseCaseTest {

    private TratamentoGateway tratamentoGateway;
    private GetTratamentoByIdUseCase getTratamentoByIdUseCase;

    @BeforeEach
    void setUp() {
        tratamentoGateway = mock(TratamentoGateway.class);
        getTratamentoByIdUseCase = new GetTratamentoByIdUseCase(tratamentoGateway);
    }

    @Test
    void deveRetornarTratamentoQuandoEncontrado() {
        Integer id = 1;
        Tratamento tratamento = new Tratamento();
        when(tratamentoGateway.findById(id)).thenReturn(Optional.of(tratamento));

        Optional<Tratamento> resultado = getTratamentoByIdUseCase.execute(id);

        assertTrue(resultado.isPresent());
        assertEquals(tratamento, resultado.get());
        verify(tratamentoGateway, times(1)).findById(id);
    }

    @Test
    void deveRetornarOptionalVazioQuandoNaoEncontrado() {
        Integer id = 2;
        when(tratamentoGateway.findById(id)).thenReturn(Optional.empty());

        Optional<Tratamento> resultado = getTratamentoByIdUseCase.execute(id);

        assertTrue(resultado.isEmpty());
        verify(tratamentoGateway, times(1)).findById(id);
    }
}
