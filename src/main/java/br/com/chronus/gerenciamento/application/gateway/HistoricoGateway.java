package br.com.chronus.gerenciamento.application.gateway;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.dto.historico.CreateHistoricoRequestDto;

import java.util.List;
import java.util.Optional;

public interface HistoricoGateway {
    Historico salvar(Historico historico);
    Optional<Historico> buscarPorId(Integer id);
    List<Historico> listarPorPaciente(Integer idPaciente);
    void deletarPorId(Integer id);
}