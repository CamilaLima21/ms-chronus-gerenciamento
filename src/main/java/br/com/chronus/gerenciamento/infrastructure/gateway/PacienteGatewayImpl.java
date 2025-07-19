package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.ContatoAnjo;
import br.com.chronus.gerenciamento.application.domain.Paciente;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.infrastructure.integration.PessoasClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PacienteGatewayImpl implements PacienteGateway {

    private final PessoasClient pessoasClient;

    @Override
    public boolean verificaPacientePorId(Integer idPaciente) {
        try {
            pessoasClient.getPacienteById(idPaciente);
            return true;
        } catch (FeignException.NotFound e) {
            return false;
        }
    }

    @Override
    public List<ContatoAnjo> getContatoAnjoPorPaciente(Integer idPaciente) {
        return pessoasClient.getContatoAnjoPorPaciente(idPaciente);
    }

    @Override
    public Paciente getPacientePorId(Integer idPaciente) {
        return pessoasClient.getPacientePorId(idPaciente);
    }

}
