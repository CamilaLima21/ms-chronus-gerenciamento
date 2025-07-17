package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.infrastructure.persistence.entity.CheckUpSaudeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CheckUpSaudeRepositoryTest {

    @Autowired
    private CheckUpSaudeRepository repository;

    @Test
    @DisplayName("Deve salvar e buscar registros por idPaciente")
    void testFindByIdPaciente() {
        CheckUpSaudeEntity checkUp1 = createCheckUp(1, 100);
        CheckUpSaudeEntity checkUp2 = createCheckUp(1, 101);
        CheckUpSaudeEntity checkUp3 = createCheckUp(2, 102);

        repository.saveAll(List.of(checkUp1, checkUp2, checkUp3));

        List<CheckUpSaudeEntity> result = repository.findByIdPaciente(1);

        assertThat(result).hasSize(2);
        assertThat(result).extracting(CheckUpSaudeEntity::getIdProfissionalSaude).containsExactlyInAnyOrder(100, 101);
    }

    @Test
    @DisplayName("Deve salvar e buscar registros por idProfissionalSaude")
    void testFindByIdProfissionalSaude() {
        CheckUpSaudeEntity checkUp1 = createCheckUp(1, 200);
        CheckUpSaudeEntity checkUp2 = createCheckUp(2, 200);
        CheckUpSaudeEntity checkUp3 = createCheckUp(3, 201);

        repository.saveAll(List.of(checkUp1, checkUp2, checkUp3));

        List<CheckUpSaudeEntity> result = repository.findByIdProfissionalSaude(200);

        assertThat(result).hasSize(2);
        assertThat(result).extracting(CheckUpSaudeEntity::getIdPaciente).containsExactlyInAnyOrder(1, 2);
    }

    private CheckUpSaudeEntity createCheckUp(Integer idPaciente, Integer idProfissionalSaude) {
        return CheckUpSaudeEntity.builder()
                .idPaciente(idPaciente)
                .idProfissionalSaude(idProfissionalSaude)
                .glicemia("90")
                .pressaoArterial("120/80")
                .frequenciaCardiaca("72")
                .frequenciaRespiratoria("18")
                .temperaturaCorporal("36.5")
                .saturacaoOxigenio("98%")
                .outrosDados("nenhum")
                .observacoes("ok")
                .dataHoraRegistro(LocalDateTime.now())
                .build();
    }
}
