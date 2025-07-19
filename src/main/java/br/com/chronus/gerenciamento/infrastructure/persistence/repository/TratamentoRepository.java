package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.TratamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TratamentoRepository extends JpaRepository<TratamentoEntity, Integer> {

    List<TratamentoEntity> findByIdPaciente(Integer idPaciente);

    @Query("SELECT DISTINCT t FROM TratamentoEntity t " +
            "LEFT JOIN FETCH t.medicamentos m " +
            "LEFT JOIN t.horarios h " +
            "WHERE t.inicioTratamento <= :data " +
            "AND t.fimTratamento >= :data " +
            "AND :horario MEMBER OF t.horarios")
    List<TratamentoEntity> buscarTratamentosAtivosNoHorario(
            @Param("data") LocalDate data,
            @Param("horario") HorarioEnum horario);

}