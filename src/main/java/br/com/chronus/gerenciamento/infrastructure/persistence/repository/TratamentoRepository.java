package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.infrastructure.persistence.entity.TratamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TratamentoRepository extends JpaRepository<TratamentoEntity, Integer> {

    List<TratamentoEntity> findByIdPaciente(Integer idPaciente);

}