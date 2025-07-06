package br.com.chronus.gerenciamento.application.usecase.tratamento;

import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetTratamentoByIdUseCase {
    private final TratamentoGateway tratamentoGateway;

    public Optional<Tratamento> execute(Integer idTratamento) {
        return tratamentoGateway.findById(idTratamento);
    }
}