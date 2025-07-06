package br.com.chronus.gerenciamento.application.gateway;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;

import java.util.List;
import java.util.Optional;

public interface CheckUpSaudeGateway {

    CheckUpSaude save(final CheckUpSaude checkUpSaude);

    Optional<CheckUpSaude> findById(final Integer idCheckUpSaude);

    CheckUpSaude update(final CheckUpSaude checkUpSaude);

    void delete(final Integer idCheckUpSaude);

    List<CheckUpSaude> findAll();

    List<CheckUpSaude> findByPacienteId(final Integer idPaciente);

    List<CheckUpSaude> findByProfissionalSaudeId(final Integer idProfissionalSaude);
}
