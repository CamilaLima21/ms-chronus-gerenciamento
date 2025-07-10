package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.dto.checkup.CheckUpSaudeRequest;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.checkup.exception.CheckUpSaudeNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCheckUpSaude {

    private final CheckUpSaudeGateway gateway;

    public CheckUpSaude execute(final Integer idCheckUp, final CheckUpSaudeRequest request) {
        final var checkUp = gateway.findById(idCheckUp)
                .orElseThrow(() -> new CheckUpSaudeNaoEncontradoException(idCheckUp));

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
