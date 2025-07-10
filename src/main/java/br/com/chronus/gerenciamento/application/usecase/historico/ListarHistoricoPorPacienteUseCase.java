package br.com.chronus.gerenciamento.application.usecase.historico;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListarHistoricoPorPacienteUseCase {

    private final HistoricoGateway historicoGateway;
    private final PacienteGateway pacienteGateway;

    public List<Historico> executar(Integer idPaciente) {

        boolean pacienteExiste = pacienteGateway.verificaPacientePorId(idPaciente);
        if (!pacienteExiste) {
            throw new PacienteNaoEncontradoException(idPaciente);
        }

        return historicoGateway.listarPorPaciente(idPaciente);
    }
}
