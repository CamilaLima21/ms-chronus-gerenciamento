package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MedicacaoEntityTest {

    @Test
    void deveCriarMedicacaoEntityComDadosCorretos() {

        Integer idMedicacao = 1;
        String nomeMedicacao = "Paracetamol";
        String descricaoMedicacao = "Analgésico e antitérmico";
        String sigtapMedicacao = "12345";

        MedicacaoEntity medicacaoEntity = MedicacaoEntity.builder()
                .idMedicacao(idMedicacao)
                .nomeMedicacao(nomeMedicacao)
                .descricaoMedicacao(descricaoMedicacao)
                .sigtapMedicacao(sigtapMedicacao)
                .build();

        assertThat(medicacaoEntity.getIdMedicacao()).isEqualTo(idMedicacao);
        assertThat(medicacaoEntity.getNomeMedicacao()).isEqualTo(nomeMedicacao);
        assertThat(medicacaoEntity.getDescricaoMedicacao()).isEqualTo(descricaoMedicacao);
        assertThat(medicacaoEntity.getSigtapMedicacao()).isEqualTo(sigtapMedicacao);
    }
}
