package br.com.chronus.gerenciamento.application.usecase.historico;

import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeletarHistoricoPorIdUseCaseTest {

    private HistoricoGateway historicoGateway;
    private DeletarHistoricoPorIdUseCase useCase;

    @BeforeEach
    void setUp() {
        historicoGateway = mock(HistoricoGateway.class);
        useCase = new DeletarHistoricoPorIdUseCase(historicoGateway);
    }

    @Test
    void deveDeletarHistoricoPorIdComSucesso() {

        Integer id = 1;

        useCase.executar(id);

        verify(historicoGateway, times(1)).deletarPorId(id);
    }
}
