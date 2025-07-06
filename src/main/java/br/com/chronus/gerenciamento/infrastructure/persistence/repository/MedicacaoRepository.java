package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.infrastructure.persistence.entity.MedicacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicacaoRepository extends JpaRepository<MedicacaoEntity, Integer> {

    Optional<MedicacaoEntity> findByNomeMedicacao(String nomeMedicacao);

    Optional<MedicacaoEntity> findBySigtapMedicacao(String sigtapMedicacao);
}