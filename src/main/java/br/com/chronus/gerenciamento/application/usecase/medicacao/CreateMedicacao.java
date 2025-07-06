package br.com.chronus.gerenciamento.application.usecase.medicacao;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.gateway.MedicacaoGateway;
import br.com.chronus.gerenciamento.application.usecase.medicacao.exception.MedicacaoExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateMedicacao {

    private final MedicacaoGateway gateway;

    public Medicacao execute(final Medicacao request) {
        final var medicacao = gateway.findMedicacaoById(request.getIdMedicacao());

        if (medicacao.isPresent()) {
            throw new MedicacaoExistenteException(
                    request.getIdMedicacao(),
                    request.getNomeMedicacao()
            );
        }

        final var buildDomain = Medicacao.createMedicacao(
                request.getNomeMedicacao(),
                request.getDescricaoMedicacao(),
                request.getSigtapMedicacao()
        );

        return gateway.save(buildDomain);
    }
}