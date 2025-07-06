package br.com.chronus.gerenciamento.application.usecase.tratamento;

import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTratamentoUseCase {
    private final TratamentoGateway tratamentoGateway;

    public void execute(Integer idTratamento) {
        tratamentoGateway.delete(idTratamento);
    }
}