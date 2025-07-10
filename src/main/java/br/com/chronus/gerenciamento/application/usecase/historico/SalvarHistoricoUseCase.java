package br.com.chronus.gerenciamento.application.usecase.historico;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.dto.historico.CreateHistoricoRequestDto;
import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import org.springframework.stereotype.Component;

@Component
public class SalvarHistoricoUseCase {
    private final HistoricoGateway historicoGateway;

    public SalvarHistoricoUseCase(HistoricoGateway historicoGateway) {
        this.historicoGateway = historicoGateway;
    }

    public Historico executar(Historico historico) {
        return historicoGateway.salvar(historico);
    }
}