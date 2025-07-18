package br.com.chronus.gerenciamento.infrastructure.persistence.entity;
import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TratamentoEntityTest {

    @Test
    void deveCriarTratamentoEntityComDadosCorretos() {

        Integer idTratamento = 1;

        MedicacaoEntity medic1 = MedicacaoEntity.builder()
                .idMedicacao(10)
                .nomeMedicacao("Medicamento A")
                .descricaoMedicacao("Descrição A")
                .sigtapMedicacao("S123")
                .build();

        MedicacaoEntity medic2 = MedicacaoEntity.builder()
                .idMedicacao(20)
                .nomeMedicacao("Medicamento B")
                .descricaoMedicacao("Descrição B")
                .sigtapMedicacao("S456")
                .build();

        List<MedicacaoEntity> medicamentos = List.of(medic1, medic2);

        LocalDate inicio = LocalDate.of(2025, 7, 1);
        LocalDate fim = LocalDate.of(2025, 7, 31);
        String periodicidade = "Diária";
        String dosagem = "2 comprimidos";
        List<HorarioEnum> horarios = List.of(HorarioEnum.H08, HorarioEnum.H20);

        TratamentoEntity tratamento = TratamentoEntity.builder()
                .idTratamento(idTratamento)
                .medicamentos(medicamentos)
                .inicioTratamento(inicio)
                .fimTratamento(fim)
                .periodicidade(periodicidade)
                .dosagem(dosagem)
                .horarios(horarios)
                .build();

        assertThat(tratamento.getIdTratamento()).isEqualTo(idTratamento);
        assertThat(tratamento.getMedicamentos())
                .hasSize(2)
                .containsExactly(medic1, medic2);
        assertThat(tratamento.getInicioTratamento()).isEqualTo(inicio);
        assertThat(tratamento.getFimTratamento()).isEqualTo(fim);
        assertThat(tratamento.getPeriodicidade()).isEqualTo(periodicidade);
        assertThat(tratamento.getDosagem()).isEqualTo(dosagem);
        assertThat(tratamento.getHorarios())
                .containsExactly(HorarioEnum.H08, HorarioEnum.H20);
    }
}
