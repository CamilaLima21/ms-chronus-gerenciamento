package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindCheckUpSaudeByPacienteId {

    private final CheckUpSaudeGateway gateway;
    private final PacienteGateway pacienteGateway;

    public List<CheckUpSaude> execute(final Integer idPaciente) {
        boolean pacienteExiste = pacienteGateway.verificaPacientePorId(idPaciente);
        if (!pacienteExiste) {
            throw new PacienteNaoEncontradoException(idPaciente);
        }

        return gateway.findByPacienteId(idPaciente);
    }
}
