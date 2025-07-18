package br.com.chronus.gerenciamento.application.gateway;

import br.com.chronus.gerenciamento.application.domain.Consulta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConsultaGateway {

    Consulta createConsulta(final Consulta consulta);
    Optional<Consulta> getConsultaById(final int idConsulta);
    void deleteConsulta(final int idConsulta);
    Consulta updateConsulta(final Consulta consulta);
    List<Consulta> findByDataConsulta(LocalDate data);
}
