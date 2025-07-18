package br.com.chronus.gerenciamento.application.usecase.historico;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BuscarHistoricoPorIdUseCase {

    private final HistoricoGateway historicoGateway;
    private final PacienteGateway pacienteGateway;

    public Optional<Historico> executar(Integer id) {
        Optional<Historico> historicoOpt = historicoGateway.buscarPorId(id);

        if (historicoOpt.isPresent()) {
            Historico historico = historicoOpt.get();

            boolean pacienteExiste = pacienteGateway.verificaPacientePorId(historico.getIdPaciente());
            if (!pacienteExiste) {
                throw new PacienteNaoEncontradoException(historico.getIdPaciente());
            }
        }

        return historicoOpt;
    }
}
