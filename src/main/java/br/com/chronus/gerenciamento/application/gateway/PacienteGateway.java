package br.com.chronus.gerenciamento.application.gateway;

import br.com.chronus.gerenciamento.application.domain.ContatoAnjo;
import br.com.chronus.gerenciamento.application.domain.Paciente;

import java.util.List;

public interface PacienteGateway {

    boolean verificaPacientePorId(Integer idPaciente);
    Paciente getPacientePorId(Integer idPaciente);
    List<ContatoAnjo> getContatoAnjoPorPaciente(Integer idPaciente);

}
