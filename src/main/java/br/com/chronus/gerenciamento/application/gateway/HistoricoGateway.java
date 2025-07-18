package br.com.chronus.gerenciamento.application.gateway;

import br.com.chronus.gerenciamento.application.domain.Historico;

import java.util.List;
import java.util.Optional;

public interface HistoricoGateway {
    Historico salvar(Historico historico);
    Optional<Historico> buscarPorId(Integer id);
    List<Historico> listarPorPaciente(Integer idPaciente);
    List<Historico> buscarTodos();
    void deletarPorId(Integer id);
}