package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.infrastructure.integration.ProfissionalSaudeClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfissionalSaudeGatewayImpl implements ProfissionalSaudeGateway {

    private final ProfissionalSaudeClient profissionalClient;

    @Override
    public boolean verificaProfissionalPorId(Integer idProfissional) {
        try {
            profissionalClient.getProfissionalById(idProfissional);
            return true;
        } catch (FeignException.NotFound e) {
            return false;
        }
    }
}
