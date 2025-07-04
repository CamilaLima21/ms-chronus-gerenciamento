package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Integer> {
}