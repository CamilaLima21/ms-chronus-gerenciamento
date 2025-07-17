package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.infrastructure.persistence.entity.MedicacaoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MedicacaoRepositoryTest {

    @Autowired
    private MedicacaoRepository medicacaoRepository;

    @Test
    @DisplayName("Deve salvar uma medicação e buscar por nome")
    void deveSalvarEBuscarPorNome() {

        MedicacaoEntity medicacao = MedicacaoEntity.builder()
                .nomeMedicacao("Dipirona")
                .descricaoMedicacao("Analgésico e antipirético")
                .sigtapMedicacao("12345")
                .build();

        MedicacaoEntity salvo = medicacaoRepository.save(medicacao);
        Optional<MedicacaoEntity> encontrado = medicacaoRepository.findByNomeMedicacao("Dipirona");

        assertThat(salvo.getIdMedicacao()).isNotNull();
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNomeMedicacao()).isEqualTo("Dipirona");
    }

    @Test
    @DisplayName("Deve buscar uma medicação por sigtap")
    void deveBuscarPorSigtap() {

        MedicacaoEntity medicacao = MedicacaoEntity.builder()
                .nomeMedicacao("Paracetamol")
                .descricaoMedicacao("Analgésico")
                .sigtapMedicacao("54321")
                .build();
        medicacaoRepository.save(medicacao);

        Optional<MedicacaoEntity> encontrado = medicacaoRepository.findBySigtapMedicacao("54321");

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNomeMedicacao()).isEqualTo("Paracetamol");
    }

    @Test
    @DisplayName("Buscar por nome que não existe deve retornar vazio")
    void buscarPorNomeNaoExistente() {
        Optional<MedicacaoEntity> encontrado = medicacaoRepository.findByNomeMedicacao("Inexistente");
        assertThat(encontrado).isNotPresent();
    }

    @Test
    @DisplayName("Buscar por sigtap que não existe deve retornar vazio")
    void buscarPorSigtapNaoExistente() {
        Optional<MedicacaoEntity> encontrado = medicacaoRepository.findBySigtapMedicacao("00000");
        assertThat(encontrado).isNotPresent();
    }
}
