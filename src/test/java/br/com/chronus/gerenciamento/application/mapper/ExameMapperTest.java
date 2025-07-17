package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.dto.exame.ExameRequest;
import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExameMapperTest {

    private final ExameMapper mapper = new ExameMapper();

    @Test
    void toDomain_shouldMapAllFieldsCorrectly() {
        ExameRequest dto = new ExameRequest();
        dto.setIdPaciente(10);
        dto.setIdProfissionalSaude(20);
        dto.setDataExame(LocalDateTime.now().plusDays(1));
        dto.setListaExames(List.of(EnumExame.GLICEMIA_JEJUM_DIABETES, EnumExame.MAPA_HIPERTENSAO)); // Ajuste o Enum correto, se necessário
        dto.setStatusExame(EnumStatusExame.AGENDADO);

        Integer id = 100;

        Exame exame = mapper.toDomain(dto, id);

        assertNotNull(exame);
        assertEquals(id, exame.getIdExame());
        assertEquals(dto.getIdPaciente(), exame.getIdPaciente());
        assertEquals(dto.getIdProfissionalSaude(), exame.getIdProfissionalSaude());
        assertEquals(dto.getDataExame(), exame.getDataExame());
        assertEquals(dto.getListaExames(), exame.getListaExames());
        assertEquals(dto.getStatusExame(), exame.getStatusExame());
    }
}
