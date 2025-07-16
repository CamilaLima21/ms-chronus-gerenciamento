package br.com.chronus.gerenciamento.application.usecase.exame;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.dto.exame.ExameRequest;
import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.exame.exception.ExameNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateExame {

    private final ExameGateway gateway;
    private final PacienteGateway pacienteGateway;
    private final ProfissionalSaudeGateway profissionalSaudeGateway;

    public Exame execute(final int idExame, final ExameRequest request) {

        final var exameFound = gateway.findExameById(idExame)
                .orElseThrow(() -> new ExameNaoEncontradoException(idExame));

        // Validação do paciente
        boolean pacienteExiste = pacienteGateway.verificaPacientePorId(request.getIdPaciente());
        if (!pacienteExiste) {
            throw new PacienteNaoEncontradoException(request.getIdPaciente());
        }

        // Validação do profissional
        boolean profissionalExiste = profissionalSaudeGateway.verificaProfissionalPorId(request.getIdProfissionalSaude());
        if (!profissionalExiste) {
            throw new ProfissionalSaudeNaoEncontradoException(request.getIdProfissionalSaude());
        }

        // Atualização dos dados
        exameFound.setIdPaciente(request.getIdPaciente());
        exameFound.setIdProfissionalSaude(request.getIdProfissionalSaude());
        exameFound.setDataExame(request.getDataExame());
        exameFound.setListaExames(request.getListaExames());
        exameFound.setStatusExame(request.getStatusExame());

        return gateway.update(exameFound);
    }
}
