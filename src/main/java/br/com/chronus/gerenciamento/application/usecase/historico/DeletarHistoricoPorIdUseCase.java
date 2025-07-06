package br.com.chronus.gerenciamento.application.usecase.historico;

import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class DeletarHistoricoPorIdUseCase {
    private final HistoricoGateway historicoGateway;

    public DeletarHistoricoPorIdUseCase(HistoricoGateway historicoGateway) {
        this.historicoGateway = historicoGateway;
    }

    public void executar(Integer id) {
        historicoGateway.deletarPorId(id);
    }
}