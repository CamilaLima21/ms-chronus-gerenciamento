package br.com.chronus.gerenciamento.application.usecase.medicacao;

import br.com.chronus.gerenciamento.application.gateway.MedicacaoGateway;
import br.com.chronus.gerenciamento.application.usecase.medicacao.exception.MedicacaoNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteMedicacao {

    private final MedicacaoGateway gateway;

    public void execute(final int idMedicacao) {
        final var medicacao = gateway.findMedicacaoById(idMedicacao)
                .orElseThrow(() -> new MedicacaoNaoEncontradaException(idMedicacao));

        gateway.delete(medicacao.getIdMedicacao());
    }
}