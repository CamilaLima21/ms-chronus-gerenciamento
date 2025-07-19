package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Integer> {
    @Query("SELECT c FROM ConsultaEntity c WHERE CAST(c.dataHoraConsulta AS date) = :data")
    List<ConsultaEntity> findByDataConsulta(@Param("data") LocalDate data);
}