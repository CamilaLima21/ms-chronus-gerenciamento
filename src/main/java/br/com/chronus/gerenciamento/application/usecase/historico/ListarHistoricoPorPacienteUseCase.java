package br.com.chronus.gerenciamento.application.usecase.historico;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ListarHistoricoPorPacienteUseCase {
    private final HistoricoGateway historicoGateway;

    public ListarHistoricoPorPacienteUseCase(HistoricoGateway historicoGateway) {
        this.historicoGateway = historicoGateway;
    }

    public List<Historico> executar(Integer idPaciente) {
        return historicoGateway.listarPorPaciente(idPaciente);
    }
}