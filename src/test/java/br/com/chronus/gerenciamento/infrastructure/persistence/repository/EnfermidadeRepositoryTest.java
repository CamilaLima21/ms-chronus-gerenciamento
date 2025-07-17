package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.EnfermidadeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EnfermidadeRepositoryTest {

    @Autowired
    private EnfermidadeRepository enfermidadeRepository;

    @Test
    void deveSalvarEEncontrarEnfermidadePorCid() {

        EnfermidadeEntity enfermidade = EnfermidadeEntity.builder()
                .enfermidade(EnumEnfermidade.ASMA)
                .descricaoEnfermidade("Asma brônquica")
                .cid("J45")
                .build();

        enfermidadeRepository.save(enfermidade);

        Optional<EnfermidadeEntity> encontrado = enfermidadeRepository.findByCid("J45");

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getCid()).isEqualTo("J45");
        assertThat(encontrado.get().getEnfermidade()).isEqualTo(EnumEnfermidade.ASMA);
    }

    @Test
    void deveSalvarEEncontrarEnfermidadePorEnum() {

        EnfermidadeEntity enfermidade = EnfermidadeEntity.builder()
                .enfermidade(EnumEnfermidade.DIABETES)
                .descricaoEnfermidade("Diabetes tipo 2")
                .cid("E11")
                .build();

        enfermidadeRepository.save(enfermidade);

        Optional<EnfermidadeEntity> encontrado = enfermidadeRepository.findByEnfermidade(EnumEnfermidade.DIABETES);

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getEnfermidade()).isEqualTo(EnumEnfermidade.DIABETES);
        assertThat(encontrado.get().getCid()).isEqualTo("E11");
    }
}
