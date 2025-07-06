package br.com.chronus.gerenciamento.application.usecase.tratamento;

import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTratamentoUseCase {
    private final TratamentoGateway tratamentoGateway;

    public Tratamento execute(Tratamento tratamento) {
        return tratamentoGateway.save(tratamento);
    }
}