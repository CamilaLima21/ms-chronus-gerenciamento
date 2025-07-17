package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.dto.enfermidades.EnfermidadeRequest;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.EnfermidadeEntity;
import org.springframework.stereotype.Component;

@Component
public class EnfermidadeMapper {

    public Enfermidade mapToDomain(EnfermidadeRequest dto, Integer id) {
        return Enfermidade.builder()
                .idEnfermidade(id)
                .enfermidade(dto.getEnfermidade())
                .descricaoEnfermidade(dto.getDescricaoEnfermidade())
                .cid(dto.getCid())
                .build();
    }

    public EnfermidadeEntity toEnfermidadeEntity(Enfermidade enfermidade) {
        EnfermidadeEntity entity = new EnfermidadeEntity();
        entity.setIdEnfermidade(enfermidade.getIdEnfermidade());
        entity.setEnfermidade(enfermidade.getEnfermidade());
        entity.setDescricaoEnfermidade(enfermidade.getDescricaoEnfermidade());
        entity.setCid(enfermidade.getCid());
        return entity;
    }

    public Enfermidade toEnfermidade(EnfermidadeEntity entity) {
        Enfermidade enfermidade = new Enfermidade();
        enfermidade.setIdEnfermidade(entity.getIdEnfermidade());
        enfermidade.setEnfermidade(entity.getEnfermidade());
        enfermidade.setDescricaoEnfermidade(entity.getDescricaoEnfermidade());
        enfermidade.setCid(entity.getCid());
        return enfermidade;
    }

}