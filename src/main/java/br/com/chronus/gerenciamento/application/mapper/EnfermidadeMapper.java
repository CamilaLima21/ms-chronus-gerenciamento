package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.dto.enfermidades.EnfermidadeRequest;
import org.springframework.stereotype.Component;

@Component
public class EnfermidadeMapper {

    public static Enfermidade mapToDomain(EnfermidadeRequest dto, Integer id) {
        return Enfermidade.builder()
                .idEnfermidade(id)
                .enfermidade(dto.getEnfermidade())
                .descricaoEnfermidade(dto.getDescricaoEnfermidade())
                .cid(dto.getCid())
                .build();
    }
}