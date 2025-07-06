package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.infrastructure.persistence.entity.HistoricoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<HistoricoEntity, Long> {
    List<HistoricoEntity> findByIdPaciente(Long idPaciente);
}