package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnfermidadeEntityTest {

    @Test
    void deveConstruirEntidadeComBuilder() {
        EnfermidadeEntity entity = EnfermidadeEntity.builder()
                .idEnfermidade(1)
                .enfermidade(EnumEnfermidade.DIABETES)
                .descricaoEnfermidade("Diabetes tipo 2")
                .cid("E11")
                .build();

        assertThat(entity.getIdEnfermidade()).isEqualTo(1);
        assertThat(entity.getEnfermidade()).isEqualTo(EnumEnfermidade.DIABETES);
        assertThat(entity.getDescricaoEnfermidade()).isEqualTo("Diabetes tipo 2");
        assertThat(entity.getCid()).isEqualTo("E11");
    }

    @Test
    void deveSetarEAcessarValoresComSetters() {
        EnfermidadeEntity entity = new EnfermidadeEntity();
        entity.setIdEnfermidade(2);
        entity.setEnfermidade(EnumEnfermidade.HIPERTENSAO_ARTERIAL);
        entity.setDescricaoEnfermidade("Hipertensão leve");
        entity.setCid("I10");

        assertThat(entity.getIdEnfermidade()).isEqualTo(2);
        assertThat(entity.getEnfermidade()).isEqualTo(EnumEnfermidade.HIPERTENSAO_ARTERIAL);
        assertThat(entity.getDescricaoEnfermidade()).isEqualTo("Hipertensão leve");
        assertThat(entity.getCid()).isEqualTo("I10");
    }

    @Test
    void deveConstruirComConstrutorCheio() {
        EnfermidadeEntity entity = new EnfermidadeEntity(
                3,
                EnumEnfermidade.ASMA,
                "Asma brônquica",
                "J45"
        );

        assertThat(entity.getIdEnfermidade()).isEqualTo(3);
        assertThat(entity.getEnfermidade()).isEqualTo(EnumEnfermidade.ASMA);
        assertThat(entity.getDescricaoEnfermidade()).isEqualTo("Asma brônquica");
        assertThat(entity.getCid()).isEqualTo("J45");
    }

    @Test
    void deveInstanciarComConstrutorVazio() {
        EnfermidadeEntity entity = new EnfermidadeEntity();
        assertThat(entity).isNotNull();
    }
}
