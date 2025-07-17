package br.com.chronus.gerenciamento.application.usecase.tratamento;

import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeleteTratamentoUseCaseTest {

    private TratamentoGateway tratamentoGateway;
    private DeleteTratamentoUseCase deleteTratamentoUseCase;

    @BeforeEach
    void setUp() {
        tratamentoGateway = mock(TratamentoGateway.class);
        deleteTratamentoUseCase = new DeleteTratamentoUseCase(tratamentoGateway);
    }

    @Test
    void deveChamarDeleteNoGatewayComIdCorreto() {
        Integer idTratamento = 123;

        deleteTratamentoUseCase.execute(idTratamento);

        verify(tratamentoGateway, times(1)).delete(idTratamento);
    }
}
