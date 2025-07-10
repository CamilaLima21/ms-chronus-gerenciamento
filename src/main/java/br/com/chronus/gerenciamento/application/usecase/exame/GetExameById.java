package br.com.chronus.gerenciamento.application.usecase.exame;

import br.com.chronus.gerenciamento.application.domain.Exame;
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
public class GetExameById {

    private final ExameGateway gateway;
    private final PacienteGateway pacienteGateway;
    private final ProfissionalSaudeGateway profissionalSaudeGateway;

    public Exame execute(final int idExame) {
        Exame exame = gateway.findExameById(idExame)
                .orElseThrow(() -> new ExameNaoEncontradoException(idExame));

        // Valida paciente
        boolean pacienteExiste = pacienteGateway.verificaPacientePorId(exame.getIdPaciente());
        if (!pacienteExiste) {
            throw new PacienteNaoEncontradoException(exame.getIdPaciente());
        }

        // Valida profissional
        boolean profissionalExiste = profissionalSaudeGateway.verificaProfissionalPorId(exame.getIdProfissionalSaude());
        if (!profissionalExiste) {
            throw new ProfissionalSaudeNaoEncontradoException(exame.getIdProfissionalSaude());
        }

        return exame;
    }
}
