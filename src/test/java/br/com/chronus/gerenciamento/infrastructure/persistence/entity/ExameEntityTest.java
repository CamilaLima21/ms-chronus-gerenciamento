package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExameEntityTest {

    @Test
    void deveCriarExameEntityComDadosCorretos() {

        Integer idPaciente = 10;
        Integer idProfissional = 20;
        LocalDateTime dataExame = LocalDateTime.now();
        List<EnumExame> exames = Arrays.asList(
                EnumExame.GLICEMIA_JEJUM_DIABETES,
                EnumExame.MAPA_HIPERTENSAO,
                EnumExame.PAPANICOLAU_CANCERES
        );
        EnumStatusExame status = EnumStatusExame.AGENDADO;

        ExameEntity exameEntity = ExameEntity.builder()
                .idExame(1)
                .idPaciente(idPaciente)
                .idProfissionalSaude(idProfissional)
                .dataExame(dataExame)
                .listaExames(exames)
                .statusExame(status)
                .build();

        assertThat(exameEntity.getIdExame()).isEqualTo(1);
        assertThat(exameEntity.getIdPaciente()).isEqualTo(idPaciente);
        assertThat(exameEntity.getIdProfissionalSaude()).isEqualTo(idProfissional);
        assertThat(exameEntity.getDataExame()).isEqualTo(dataExame);
        assertThat(exameEntity.getListaExames()).containsExactlyElementsOf(exames);
        assertThat(exameEntity.getStatusExame()).isEqualTo(status);
    }
}
