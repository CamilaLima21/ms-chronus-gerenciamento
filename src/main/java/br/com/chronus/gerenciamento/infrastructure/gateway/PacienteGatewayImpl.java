package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.infrastructure.integration.PacienteClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PacienteGatewayImpl implements PacienteGateway {

    private final PacienteClient pacienteClient;

    @Override
    public boolean verificaPacientePorId(Integer idPaciente) {
        try {
            pacienteClient.getPacienteById(idPaciente); // 200 = ok
            return true;
        } catch (FeignException.NotFound e) {
            return false;
        }
    }
}
