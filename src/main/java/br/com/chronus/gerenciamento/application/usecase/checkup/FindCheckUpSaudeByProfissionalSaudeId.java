package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindCheckUpSaudeByProfissionalSaudeId {

    private final CheckUpSaudeGateway gateway;
    private final ProfissionalSaudeGateway profissionalSaudeGateway;

    public List<CheckUpSaude> execute(final Integer idProfissionalSaude) {
        boolean profissionalExiste = profissionalSaudeGateway.verificaProfissionalPorId(idProfissionalSaude);
        if (!profissionalExiste) {
            throw new ProfissionalSaudeNaoEncontradoException(idProfissionalSaude);
        }

        return gateway.findByProfissionalSaudeId(idProfissionalSaude);
    }
}
