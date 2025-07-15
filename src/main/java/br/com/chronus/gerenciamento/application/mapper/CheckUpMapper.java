package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.dto.checkup.CheckUpSaudeRequest;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.CheckUpSaudeEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CheckUpMapper {

    public CheckUpSaude toDomain(CheckUpSaudeRequest dto, Integer id) {
        return CheckUpSaude.builder()
                .idCheckUpsaude(id)
                .idPaciente(dto.getIdPaciente())
                .idProfissionalSaude(dto.getIdProfissionalSaude())
                .glicemia(dto.getGlicemia())
                .pressaoArterial(dto.getPressaoArterial())
                .frequenciaCardiaca(dto.getFrequenciaCardiaca())
                .frequenciaRespiratoria(dto.getFrequenciaRespiratoria())
                .temperaturaCorporal(dto.getTemperaturaCorporal())
                .saturacaoOxigenio(dto.getSaturacaoOxigenio())
                .outrosDados(dto.getOutrosDados())
                .observacoes(dto.getObservacoes())
                .dataHoraRegistro(LocalDateTime.now())
                .build();
    }

    public CheckUpSaude mapToDomain(final CheckUpSaudeEntity entity) {
        return CheckUpSaude.builder()
                .idCheckUpsaude(entity.getIdCheckUpsaude())
                .idPaciente(entity.getIdPaciente())
                .idProfissionalSaude(entity.getIdProfissionalSaude())
                .glicemia(entity.getGlicemia())
                .pressaoArterial(entity.getPressaoArterial())
                .frequenciaCardiaca(entity.getFrequenciaCardiaca())
                .frequenciaRespiratoria(entity.getFrequenciaRespiratoria())
                .temperaturaCorporal(entity.getTemperaturaCorporal())
                .saturacaoOxigenio(entity.getSaturacaoOxigenio())
                .outrosDados(entity.getOutrosDados())
                .observacoes(entity.getObservacoes())
                .dataHoraRegistro(entity.getDataHoraRegistro())
                .build();
    }

    public CheckUpSaudeEntity mapToEntity(final CheckUpSaude domain) {
        return CheckUpSaudeEntity.builder()
                .idCheckUpsaude(domain.getIdCheckUpsaude())
                .idPaciente(domain.getIdPaciente())
                .idProfissionalSaude(domain.getIdProfissionalSaude())
                .glicemia(domain.getGlicemia())
                .pressaoArterial(domain.getPressaoArterial())
                .frequenciaCardiaca(domain.getFrequenciaCardiaca())
                .frequenciaRespiratoria(domain.getFrequenciaRespiratoria())
                .temperaturaCorporal(domain.getTemperaturaCorporal())
                .saturacaoOxigenio(domain.getSaturacaoOxigenio())
                .outrosDados(domain.getOutrosDados())
                .observacoes(domain.getObservacoes())
                .dataHoraRegistro(domain.getDataHoraRegistro())
                .build();
    }
}