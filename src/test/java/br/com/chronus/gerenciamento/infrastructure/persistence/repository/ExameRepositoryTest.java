package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ExameEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ExameRepositoryTest {

    @Autowired
    private ExameRepository exameRepository;

    @Test
    void deveSalvarEBuscarPorIdExame() {

        ExameEntity exame = ExameEntity.builder()
                .idPaciente(1)
                .idProfissionalSaude(100)
                .dataExame(LocalDateTime.now())
                .listaExames(Arrays.asList(EnumExame.GLICEMIA_JEJUM_DIABETES, EnumExame.IMC_OBESIDADE))
                .statusExame(EnumStatusExame.AGENDADO)
                .build();

        ExameEntity salvo = exameRepository.save(exame);

        Optional<ExameEntity> encontrado = exameRepository.findByIdExame(salvo.getIdExame());

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getIdExame()).isEqualTo(salvo.getIdExame());
    }

    @Test
    void deveBuscarPorIdPaciente() {

        ExameEntity exame1 = ExameEntity.builder()
                .idPaciente(2)
                .idProfissionalSaude(101)
                .dataExame(LocalDateTime.now())
                .listaExames(List.of(EnumExame.ESPIROMETRIA_ASMA))
                .statusExame(EnumStatusExame.CONCLUIDO)
                .build();

        ExameEntity exame2 = ExameEntity.builder()
                .idPaciente(2)
                .idProfissionalSaude(102)
                .dataExame(LocalDateTime.now())
                .listaExames(List.of(EnumExame.MAPA_HIPERTENSAO))
                .statusExame(EnumStatusExame.AGENDADO)
                .build();

        exameRepository.saveAll(List.of(exame1, exame2));

        List<ExameEntity> examesPaciente2 = exameRepository.findByIdPaciente(2);

        assertThat(examesPaciente2).hasSize(2);
        assertThat(examesPaciente2).extracting("idPaciente").containsOnly(2);
    }

    @Test
    void deveBuscarPorStatusExame() {

        ExameEntity exame1 = ExameEntity.builder()
                .idPaciente(3)
                .idProfissionalSaude(103)
                .dataExame(LocalDateTime.now())
                .listaExames(List.of(EnumExame.TGO_TGP_HEPATICA))
                .statusExame(EnumStatusExame.EM_ANDAMENTO)
                .build();

        ExameEntity exame2 = ExameEntity.builder()
                .idPaciente(4)
                .idProfissionalSaude(104)
                .dataExame(LocalDateTime.now())
                .listaExames(List.of(EnumExame.CREATININA_RENAL))
                .statusExame(EnumStatusExame.EM_ANDAMENTO)
                .build();

        exameRepository.saveAll(List.of(exame1, exame2));

        List<ExameEntity> examesEmAndamento = exameRepository.findByStatusExame(EnumStatusExame.EM_ANDAMENTO);

        assertThat(examesEmAndamento).hasSize(2);
        assertThat(examesEmAndamento).extracting("statusExame").containsOnly(EnumStatusExame.EM_ANDAMENTO);
    }
}
