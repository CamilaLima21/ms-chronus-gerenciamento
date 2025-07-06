package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExameRepository extends JpaRepository<ExameEntity, Integer> {

    Optional<ExameEntity> findByIdExame(Integer idExame);

    List<ExameEntity> findByIdPaciente(Integer idPaciente);

    List<ExameEntity> findByStatusExame(EnumStatusExame statusExame);
}