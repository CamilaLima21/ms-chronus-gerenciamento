package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.dto.exame.ExameRequest;
import org.springframework.stereotype.Component;

@Component
public class ExameMapper {

    public Exame toDomain(ExameRequest dto, Integer id) {
        return Exame.builder()
                .idExame(id)
                .idPaciente(dto.getIdPaciente())
                .idProfissionalSaude(dto.getIdProfissionalSaude())
                .dataExame(dto.getDataExame())
                .listaExames(dto.getListaExames())
                .statusExame(dto.getStatusExame())
                .build();
    }
}