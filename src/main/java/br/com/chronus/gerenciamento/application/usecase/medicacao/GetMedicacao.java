package br.com.chronus.gerenciamento.application.usecase.medicacao;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.gateway.MedicacaoGateway;
import br.com.chronus.gerenciamento.application.usecase.medicacao.exception.MedicacaoNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMedicacao {

    private final MedicacaoGateway gateway;

    public Medicacao execute(final int idMedicacao) {
        return gateway.findMedicacaoById(idMedicacao)
                .orElseThrow(() -> new MedicacaoNaoEncontradaException(idMedicacao));
    }
}