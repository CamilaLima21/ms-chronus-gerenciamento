package br.com.chronus.gerenciamento.application.usecase.medicacao;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.dto.medicacao.UpdateMedicacaoRequest;
import br.com.chronus.gerenciamento.application.gateway.MedicacaoGateway;
import br.com.chronus.gerenciamento.application.usecase.medicacao.exception.MedicacaoNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateMedicacao {

    private final MedicacaoGateway gateway;

    public Medicacao execute(final int idMedicacao, final UpdateMedicacaoRequest updateMedicacaoRequest) {
        final var medicacaoFound = gateway.findMedicacaoById(idMedicacao)
                .orElseThrow(() -> new MedicacaoNaoEncontradaException(idMedicacao));

        medicacaoFound.setNomeMedicacao(updateMedicacaoRequest.getNomeMedicacao());
        medicacaoFound.setDescricaoMedicacao(updateMedicacaoRequest.getDescricaoMedicacao());
        medicacaoFound.setSigtapMedicacao(updateMedicacaoRequest.getSigtapMedicacao());

        return gateway.update(medicacaoFound);
    }
}