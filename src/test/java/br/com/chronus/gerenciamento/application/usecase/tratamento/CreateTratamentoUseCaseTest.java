package br.com.chronus.gerenciamento.application.usecase.tratamento;

import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateTratamentoUseCaseTest {

    private TratamentoGateway tratamentoGateway;
    private CreateTratamentoUseCase createTratamentoUseCase;

    @BeforeEach
    void setup() {
        tratamentoGateway = mock(TratamentoGateway.class);
        createTratamentoUseCase = new CreateTratamentoUseCase(tratamentoGateway);
    }

    @Test
    void deveSalvarTratamentoComSucesso() {
        Tratamento tratamento = new Tratamento();

        when(tratamentoGateway.save(tratamento)).thenReturn(tratamento);

        Tratamento resultado = createTratamentoUseCase.execute(tratamento);

        assertNotNull(resultado);
        assertEquals(tratamento, resultado);

        verify(tratamentoGateway, times(1)).save(tratamento);
    }
}
