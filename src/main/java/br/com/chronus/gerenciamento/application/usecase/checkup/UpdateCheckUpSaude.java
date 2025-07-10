package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.dto.checkup.CheckUpSaudeRequest;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.checkup.exception.CheckUpSaudeNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCheckUpSaude {

    private final CheckUpSaudeGateway gateway;
    private final PacienteGateway pacienteGateway;
    private final ProfissionalSaudeGateway profissionalSaudeGateway;

    public CheckUpSaude execute(final Integer idCheckUp, final CheckUpSaudeRequest request) {

        final var checkUp = gateway.findById(idCheckUp)
                .orElseThrow(() -> new CheckUpSaudeNaoEncontradoException(idCheckUp));

        // Validação do paciente
        boolean pacienteExiste = pacienteGateway.verificaPacientePorId(request.getIdPaciente());
        if (!pacienteExiste) {
            throw new PacienteNaoEncontradoException(request.getIdPaciente());
        }

        // Validação condicional do profissional de saúde (caso informado)
        if (request.getIdProfissionalSaude() != null) {
            boolean profissionalExiste = profissionalSaudeGateway.verificaProfissionalPorId(request.getIdProfissionalSaude());
            if (!profissionalExiste) {
                throw new ProfissionalSaudeNaoEncontradoException(request.getIdProfissionalSaude());
            }
        }

        // Atualização dos campos
        checkUp.setIdPaciente(request.getIdPaciente());
        checkUp.setIdProfissionalSaude(request.getIdProfissionalSaude());
        checkUp.setGlicemia(request.getGlicemia());
        checkUp.setPressaoArterial(request.getPressaoArterial());
        checkUp.setFrequenciaCardiaca(request.getFrequenciaCardiaca());
        checkUp.setFrequenciaRespiratoria(request.getFrequenciaRespiratoria());
        checkUp.setTemperaturaCorporal(request.getTemperaturaCorporal());
        checkUp.setSaturacaoOxigenio(request.getSaturacaoOxigenio());
        checkUp.setOutrosDados(request.getOutrosDados());
        checkUp.setObservacoes(request.getObservacoes());

        return gateway.update(checkUp);
    }
}
