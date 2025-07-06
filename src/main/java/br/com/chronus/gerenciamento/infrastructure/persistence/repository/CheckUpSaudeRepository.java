package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.infrastructure.persistence.entity.CheckUpSaudeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckUpSaudeRepository extends JpaRepository<CheckUpSaudeEntity, Integer> {

    List<CheckUpSaudeEntity> findByIdPaciente(Integer idPaciente);

    List<CheckUpSaudeEntity> findByIdProfissionalSaude(Integer idProfissionalSaude);
}
