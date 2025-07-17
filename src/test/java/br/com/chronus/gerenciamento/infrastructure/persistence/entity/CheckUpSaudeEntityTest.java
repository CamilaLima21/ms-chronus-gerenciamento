package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CheckUpSaudeEntityTest {

    @Test
    void testGetterSetterAndBuilder() {
        LocalDateTime now = LocalDateTime.now();

        CheckUpSaudeEntity entity = CheckUpSaudeEntity.builder()
                .idCheckUpsaude(1)
                .idPaciente(100)
                .idProfissionalSaude(200)
                .glicemia("90 mg/dL")
                .pressaoArterial("120/80 mmHg")
                .frequenciaCardiaca("75 bpm")
                .frequenciaRespiratoria("16 rpm")
                .temperaturaCorporal("36.7 C")
                .saturacaoOxigenio("98%")
                .outrosDados("Nenhum")
                .observacoes("Paciente está bem")
                .dataHoraRegistro(now)
                .build();

        assertThat(entity.getIdCheckUpsaude()).isEqualTo(1);
        assertThat(entity.getIdPaciente()).isEqualTo(100);
        assertThat(entity.getIdProfissionalSaude()).isEqualTo(200);
        assertThat(entity.getGlicemia()).isEqualTo("90 mg/dL");
        assertThat(entity.getPressaoArterial()).isEqualTo("120/80 mmHg");
        assertThat(entity.getFrequenciaCardiaca()).isEqualTo("75 bpm");
        assertThat(entity.getFrequenciaRespiratoria()).isEqualTo("16 rpm");
        assertThat(entity.getTemperaturaCorporal()).isEqualTo("36.7 C");
        assertThat(entity.getSaturacaoOxigenio()).isEqualTo("98%");
        assertThat(entity.getOutrosDados()).isEqualTo("Nenhum");
        assertThat(entity.getObservacoes()).isEqualTo("Paciente está bem");
        assertThat(entity.getDataHoraRegistro()).isEqualTo(now);

        entity.setGlicemia("95 mg/dL");
        assertThat(entity.getGlicemia()).isEqualTo("95 mg/dL");

        entity.setObservacoes("Alterado");
        assertThat(entity.getObservacoes()).isEqualTo("Alterado");
    }
}
