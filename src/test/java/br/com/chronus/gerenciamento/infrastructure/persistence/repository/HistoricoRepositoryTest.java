package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.infrastructure.persistence.entity.HistoricoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HistoricoRepositoryTest {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Test
    void deveSalvarEConsultarPorIdPaciente() {

        HistoricoEntity historico = HistoricoEntity.builder()
                .idPaciente(123)
                .observacoes("Teste observações")
                .dataInicio(LocalDate.now().minusDays(10))
                .dataFim(LocalDate.now())
                .build();

        HistoricoEntity salvo = historicoRepository.save(historico);

        List<HistoricoEntity> encontrados = historicoRepository.findByIdPaciente(123L);

        assertThat(encontrados)
                .isNotEmpty()
                .contains(salvo);

        assertThat(encontrados.get(0).getIdPaciente()).isEqualTo(123);
    }
}
