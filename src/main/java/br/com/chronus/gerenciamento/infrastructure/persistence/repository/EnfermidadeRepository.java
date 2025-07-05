package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.infrastructure.persistence.entity.EnfermidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnfermidadeRepository extends JpaRepository<EnfermidadeEntity, Integer> {
}