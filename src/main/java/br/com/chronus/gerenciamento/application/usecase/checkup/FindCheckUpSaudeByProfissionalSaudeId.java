package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindCheckUpSaudeByProfissionalSaudeId {

    private final CheckUpSaudeGateway gateway;

    public List<CheckUpSaude> execute(final Integer idProfissionalSaude) {
        return gateway.findByProfissionalSaudeId(idProfissionalSaude);
    }
}
