package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.infrastructure.persistence.entity.TratamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TratamentoRepository extends JpaRepository<TratamentoEntity, Integer> {
}