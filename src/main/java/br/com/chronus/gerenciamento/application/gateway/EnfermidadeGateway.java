package br.com.chronus.gerenciamento.application.gateway;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;

import java.util.List;
import java.util.Optional;

public interface EnfermidadeGateway {

    Enfermidade save(final Enfermidade enfermidade);

    Optional<Enfermidade> findEnfermidadeById(final int idEnfermidade);

    Enfermidade update(final Enfermidade enfermidade);

    void delete(final Integer idEnfermidade);

    List<Enfermidade> findAll();
}
