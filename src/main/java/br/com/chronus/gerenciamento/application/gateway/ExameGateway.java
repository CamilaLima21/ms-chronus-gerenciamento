package br.com.chronus.gerenciamento.application.gateway;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;

import java.util.List;
import java.util.Optional;

public interface ExameGateway {

    Exame save(final Exame exame);

    Optional<Exame> findExameById(final int idExame);

    Exame update(final Exame exame);

    void delete(final Integer idExame);

    List<Exame> findAll();

    List<Exame> findExamesByPaciente(final Integer idPaciente);

    List<Exame> findExamesByProfissionalSaude(final Integer idProfissionalSaude);

    List<Exame> findExamesByStatus(final EnumStatusExame statusExame);

    List<Exame> findExamesByTipo(final EnumExame tipoExame);
}