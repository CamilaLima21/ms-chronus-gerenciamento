package br.com.chronus.gerenciamento.application.mapper;

<<<<<<< HEAD
=======
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ConsultaEntity;
import org.springframework.stereotype.Component;
>>>>>>> ee25d44ecb2ed8c9df96623ed5ce0c6866207022
import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.dto.consulta.ConsultaRequest;
import org.springframework.stereotype.Component;

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

    public ConsultaEntity toConsultaEntity(Consulta consulta) {
        ConsultaEntity entity = new ConsultaEntity();
        entity.setIdConsulta(consulta.getIdConsulta());
        entity.setIdPaciente(consulta.getIdPaciente());
        entity.setIdProfissionalSaude(consulta.getIdProfissionalSaude());
        entity.setDataHoraConsulta(consulta.getDataHoraConsulta());
        entity.setObservacaoConsulta(consulta.getObservacaoConsulta());
        entity.setStatusConsulta(consulta.getStatusConsulta());
        entity.setTipoConsulta(consulta.getTipoConsulta());
        entity.setMotivoCancelamento(consulta.getMotivoCancelamento());
        return entity;
    }

    public Consulta toConsulta(ConsultaEntity entity) {
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(entity.getIdConsulta());
        consulta.setIdPaciente(entity.getIdPaciente());
        consulta.setIdProfissionalSaude(entity.getIdProfissionalSaude());
        consulta.setDataHoraConsulta(entity.getDataHoraConsulta());
        consulta.setObservacaoConsulta(entity.getObservacaoConsulta());
        consulta.setStatusConsulta(entity.getStatusConsulta());
        consulta.setTipoConsulta(entity.getTipoConsulta());
        consulta.setMotivoCancelamento(entity.getMotivoCancelamento());
        return consulta;
    }

}