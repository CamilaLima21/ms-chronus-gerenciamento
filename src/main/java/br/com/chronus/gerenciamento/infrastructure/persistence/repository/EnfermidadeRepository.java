package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.EnfermidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnfermidadeRepository extends JpaRepository<EnfermidadeEntity, Integer> {

    Optional<EnfermidadeEntity> findByCid(String cid);

    Optional<EnfermidadeEntity> findByEnfermidade(EnumEnfermidade enfermidade);
}