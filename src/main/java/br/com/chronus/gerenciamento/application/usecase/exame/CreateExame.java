package br.com.chronus.gerenciamento.application.usecase.exame;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.exame.exception.ExameExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateExame {

    private final ExameGateway gateway;
    private final PacienteGateway pacienteGateway;
    private final ProfissionalSaudeGateway profissionalSaudeGateway;

    public Exame execute(final Exame request) {

        final var exame = gateway.findExameById(request.getIdExame());
        if (exame.isPresent()) {
            throw new ExameExistenteException(request.getIdExame());
        }

        // Validação do paciente
        boolean pacienteExiste = pacienteGateway.verificaPacientePorId(request.getIdPaciente());
        if (!pacienteExiste) {
            throw new PacienteNaoEncontradoException(request.getIdPaciente());
        }

        // Validação do profissional de saúde
        boolean profissionalExiste = profissionalSaudeGateway.verificaProfissionalPorId(request.getIdProfissionalSaude());
        if (!profissionalExiste) {
            throw new ProfissionalSaudeNaoEncontradoException(request.getIdProfissionalSaude());
        }

        final var buildDomain = Exame.createExame(
                request.getIdPaciente(),
                request.getIdProfissionalSaude(),
                request.getDataExame(),
                request.getListaExames(),
                request.getStatusExame()
        );

        return gateway.save(buildDomain);
    }
}
