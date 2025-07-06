package br.com.chronus.gerenciamento.application.usecase.tratamento;

import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTratamentosUseCase {
    private final TratamentoGateway tratamentoGateway;

    public List<Tratamento> execute() {
        return tratamentoGateway.findAll();
    }
}