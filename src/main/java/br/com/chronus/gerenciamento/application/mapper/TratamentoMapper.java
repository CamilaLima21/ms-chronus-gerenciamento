package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.TratamentoEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TratamentoMapper {

    private final MedicacaoMapper medicacaoMapper;

    public TratamentoMapper(MedicacaoMapper medicacaoMapper) {
        this.medicacaoMapper = medicacaoMapper;
    }

    public TratamentoEntity mapToEntity(Tratamento tratamento) {
        return TratamentoEntity.builder()
                .idTratamento(tratamento.getIdTratamento())
                .idPaciente(tratamento.getIdPaciente())
                .medicamentos(
                        tratamento.getMedicamentos() != null ?
                                tratamento.getMedicamentos().stream()
                                        .map(medicacaoMapper::mapMedicamentoToEntity)
                                        .collect(Collectors.toList())
                                : null
                )
                .inicioTratamento(tratamento.getInicioTratamento())
                .fimTratamento(tratamento.getFimTratamento())
                .periodicidade(tratamento.getPeriodicidade())
                .dosagem(tratamento.getDosagem())
                .horarios(tratamento.getHorarios())
                .build();
    }

    public Tratamento mapToDomain(TratamentoEntity entity) {
        return Tratamento.builder()
                .idTratamento(entity.getIdTratamento())
                .idPaciente(entity.getIdPaciente())
                .medicamentos(
                        entity.getMedicamentos() != null ?
                                entity.getMedicamentos().stream()
                                        .map(medicacaoMapper::mapEntityToMedicamento)
                                        .collect(Collectors.toList())
                                : null
                )
                .inicioTratamento(entity.getInicioTratamento())
                .fimTratamento(entity.getFimTratamento())
                .periodicidade(entity.getPeriodicidade())
                .dosagem(entity.getDosagem())
                .horarios(entity.getHorarios())
                .build();
    }
}
