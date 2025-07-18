package br.com.chronus.gerenciamento.application.usecase.historico;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarTodosHistoricosUseCase {

    private final HistoricoGateway historicoGateway;

    public List<Historico> executar() {
        return historicoGateway.buscarTodos();
    }
}