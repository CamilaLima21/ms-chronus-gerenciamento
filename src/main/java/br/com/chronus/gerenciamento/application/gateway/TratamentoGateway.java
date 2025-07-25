package br.com.chronus.gerenciamento.application.gateway;

import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.TratamentoEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TratamentoGateway {

    Tratamento save(final Tratamento tratamento);
    Optional<Tratamento> findById(final Integer idTratamento);
    Tratamento update(final Tratamento tratamento);
    void delete(final Integer idTratamento);
    List<Tratamento> findAll();
    List<Tratamento> findByPacienteId(final Integer idPaciente);
    List<TratamentoEntity> findByPeriodoAndHorario(LocalDate data, HorarioEnum horario);
}