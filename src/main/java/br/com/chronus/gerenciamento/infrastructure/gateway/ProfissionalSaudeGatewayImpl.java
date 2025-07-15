package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.infrastructure.integration.PessoasClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfissionalSaudeGatewayImpl implements ProfissionalSaudeGateway {

    private final PessoasClient pessoasClient;

    @Override
    public boolean verificaProfissionalPorId(Integer idProfissional) {
        try {
            pessoasClient.getProfissionalById(idProfissional);
            return true;
        } catch (FeignException.NotFound e) {
            return false;
        }
    }
}
