package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.checkup.exception.CheckUpSaudeExistenteException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCheckUpSaude {

    private final CheckUpSaudeGateway gateway;
    private final PacienteGateway pacienteGateway;
    private final ProfissionalSaudeGateway profissionalSaudeGateway;

    public CheckUpSaude execute(final CheckUpSaude request) {

        // Verifica se o Check-Up já existe
        final var checkUpExistente = gateway.findById(request.getIdCheckUpsaude());
        if (checkUpExistente.isPresent()) {
            throw new CheckUpSaudeExistenteException(request.getIdCheckUpsaude());
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

        // Criação do novo Check-Up
        final var novoCheckUp = CheckUpSaude.createCheckUpSaude(
                request.getIdPaciente(),
                request.getIdProfissionalSaude(),
                request.getGlicemia(),
                request.getPressaoArterial(),
                request.getFrequenciaCardiaca(),
                request.getFrequenciaRespiratoria(),
                request.getTemperaturaCorporal(),
                request.getSaturacaoOxigenio(),
                request.getOutrosDados(),
                request.getObservacoes()
        );

        return gateway.save(novoCheckUp);
    }
}
