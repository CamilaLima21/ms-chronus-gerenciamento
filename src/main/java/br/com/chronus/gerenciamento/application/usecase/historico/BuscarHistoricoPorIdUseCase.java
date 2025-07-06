package br.com.chronus.gerenciamento.application.usecase.historico;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BuscarHistoricoPorIdUseCase {
    private final HistoricoGateway historicoGateway;

    public BuscarHistoricoPorIdUseCase(HistoricoGateway historicoGateway) {
        this.historicoGateway = historicoGateway;
    }

    public Optional<Historico> executar(Integer id) {
        return historicoGateway.buscarPorId(id);
    }
}