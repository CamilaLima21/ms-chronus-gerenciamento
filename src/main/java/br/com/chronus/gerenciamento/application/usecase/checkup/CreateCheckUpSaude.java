package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.checkup.exception.CheckUpSaudeExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCheckUpSaude {

    private final CheckUpSaudeGateway gateway;

    public CheckUpSaude execute(final CheckUpSaude request) {
        final var checkUpExistente = gateway.findById(request.getIdCheckUpsaude());

        if (checkUpExistente.isPresent()) {
            throw new CheckUpSaudeExistenteException(request.getIdCheckUpsaude());
        }

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
