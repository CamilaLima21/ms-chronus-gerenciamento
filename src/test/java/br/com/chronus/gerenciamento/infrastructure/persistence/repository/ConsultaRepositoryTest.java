package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ConsultaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ConsultaRepositoryTest {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Test
    void deveSalvarEBuscarConsultaEntity() {

        ConsultaEntity consulta = ConsultaEntity.builder()
                .idPaciente(1)
                .idProfissionalSaude(2)
                .dataHoraConsulta(LocalDate.now())
                .observacaoConsulta("Consulta teste")
                .statusConsulta(EnumStatusConsulta.PENDENTE)
                .tipoConsulta(EnumTipoConsulta.PRESENCIAL)
                .build();

        ConsultaEntity salvo = consultaRepository.save(consulta);
        ConsultaEntity encontrado = consultaRepository.findById(salvo.getIdConsulta()).orElse(null);

        assertThat(encontrado).isNotNull();
        assertThat(encontrado.getIdPaciente()).isEqualTo(1);
        assertThat(encontrado.getIdProfissionalSaude()).isEqualTo(2);
        assertThat(encontrado.getObservacaoConsulta()).isEqualTo("Consulta teste");
        assertThat(encontrado.getStatusConsulta()).isEqualTo(EnumStatusConsulta.PENDENTE);
        assertThat(encontrado.getTipoConsulta()).isEqualTo(EnumTipoConsulta.PRESENCIAL);
    }
}
