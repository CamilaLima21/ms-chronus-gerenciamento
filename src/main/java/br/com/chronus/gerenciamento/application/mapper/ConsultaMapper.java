package br.com.chronus.gerenciamento.application.mapper;

import org.springframework.stereotype.Component;
import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.dto.consulta.ConsultaRequest;

@Component
public class ConsultaMapper {

    public Consulta toDomain(ConsultaRequest dto, Integer idConsulta) {
        return Consulta.builder()
                .idConsulta(idConsulta)
                .idPaciente(dto.getIdPaciente())
                .idProfissionalSaude(dto.getIdProfissionalSaude())
                .dataHoraConsulta(dto.getDataHoraConsulta())
                .observacaoConsulta(dto.getObservacaoConsulta())
                .statusConsulta(dto.getStatusConsulta())
                .tipoConsulta(dto.getTipoConsulta())
                .motivoCancelamento(dto.getMotivoCancelamento())
                .build();
    }
}