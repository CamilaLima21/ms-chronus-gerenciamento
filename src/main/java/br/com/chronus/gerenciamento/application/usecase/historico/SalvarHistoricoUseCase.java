package br.com.chronus.gerenciamento.application.usecase.historico;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalvarHistoricoUseCase {

    private final HistoricoGateway historicoGateway;
    private final PacienteGateway pacienteGateway;

    public Historico executar(Historico historico) {
        boolean pacienteExiste = pacienteGateway.verificaPacientePorId(historico.getIdPaciente());
        if (!pacienteExiste) {
            throw new PacienteNaoEncontradoException(historico.getIdPaciente());
        }

        return historicoGateway.salvar(historico);
    }
}
