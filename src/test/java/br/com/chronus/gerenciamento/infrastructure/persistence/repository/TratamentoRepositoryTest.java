package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.MedicacaoEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.TratamentoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TratamentoRepositoryTest {

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @Test
    @DisplayName("Deve salvar um tratamento com dados completos")
    void deveSalvarTratamento() {

        MedicacaoEntity medicacao = MedicacaoEntity.builder()
                .nomeMedicacao("Dipirona")
                .descricaoMedicacao("Analgésico")
                .sigtapMedicacao("12345")
                .build();

        TratamentoEntity tratamento = TratamentoEntity.builder()
                .inicioTratamento(LocalDate.now().minusDays(5))
                .fimTratamento(LocalDate.now().plusDays(10))
                .periodicidade("Diária")
                .dosagem("2 comprimidos")
                .horarios(Arrays.asList(HorarioEnum.H08, HorarioEnum.H20))
                .medicamentos(List.of(medicacao))
                .build();

        TratamentoEntity salvo = tratamentoRepository.save(tratamento);

        assertThat(salvo.getIdTratamento()).isNotNull();
        assertThat(salvo.getInicioTratamento()).isEqualTo(tratamento.getInicioTratamento());
        assertThat(salvo.getFimTratamento()).isEqualTo(tratamento.getFimTratamento());
        assertThat(salvo.getPeriodicidade()).isEqualTo("Diária");
        assertThat(salvo.getDosagem()).isEqualTo("2 comprimidos");
        assertThat(salvo.getHorarios()).containsExactlyElementsOf(Arrays.asList(HorarioEnum.H08, HorarioEnum.H20));
        assertThat(salvo.getMedicamentos()).hasSize(1);
        assertThat(salvo.getMedicamentos().get(0).getNomeMedicacao()).isEqualTo("Dipirona");
    }

    @Test
    @DisplayName("Deve buscar um tratamento por ID")
    void deveBuscarPorId() {

        TratamentoEntity tratamento = TratamentoEntity.builder()
                .inicioTratamento(LocalDate.now())
                .fimTratamento(LocalDate.now().plusDays(7))
                .periodicidade("Semanal")
                .dosagem("1 comprimido")
                .horarios(List.of(HorarioEnum.H09))
                .build();

        TratamentoEntity salvo = tratamentoRepository.save(tratamento);

        Optional<TratamentoEntity> encontrado = tratamentoRepository.findById(salvo.getIdTratamento());

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getPeriodicidade()).isEqualTo("Semanal");
    }

    @Test
    @DisplayName("Deve deletar um tratamento")
    void deveDeletarTratamento() {

        TratamentoEntity tratamento = TratamentoEntity.builder()
                .inicioTratamento(LocalDate.now())
                .fimTratamento(LocalDate.now().plusDays(3))
                .periodicidade("Diária")
                .dosagem("1 comprimido")
                .horarios(List.of(HorarioEnum.H10))
                .build();

        TratamentoEntity salvo = tratamentoRepository.save(tratamento);
        Integer id = salvo.getIdTratamento();

        tratamentoRepository.deleteById(id);

        Optional<TratamentoEntity> deletado = tratamentoRepository.findById(id);
        assertThat(deletado).isNotPresent();
    }
}
