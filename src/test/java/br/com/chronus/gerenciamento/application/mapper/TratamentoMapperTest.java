package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.MedicacaoEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.TratamentoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TratamentoMapperTest {

    private MedicacaoMapper medicacaoMapper;
    private TratamentoMapper tratamentoMapper;

    @BeforeEach
    void setup() {
        medicacaoMapper = mock(MedicacaoMapper.class);
        tratamentoMapper = new TratamentoMapper(medicacaoMapper);
    }

    @Test
    void mapToEntity_deveConverterCorretamente() {
        Medicacao medicacao = Medicacao.builder()
                .idMedicacao(1)
                .nomeMedicacao("Paracetamol")
                .descricaoMedicacao("Para febre")
                .sigtapMedicacao("123456")
                .build();

        MedicacaoEntity medicacaoEntity = MedicacaoEntity.builder()
                .idMedicacao(1)
                .nomeMedicacao("Paracetamol")
                .descricaoMedicacao("Para febre")
                .sigtapMedicacao("123456")
                .build();

        when(medicacaoMapper.mapMedicamentoToEntity(medicacao)).thenReturn(medicacaoEntity);

        Tratamento tratamento = Tratamento.builder()
                .idTratamento(10)
                .idPaciente(99)
                .medicamentos(List.of(medicacao))
                .inicioTratamento(LocalDate.of(2025, 7, 1))
                .fimTratamento(LocalDate.of(2025, 7, 15))
                .periodicidade("2 vezes ao dia")
                .dosagem("500mg")
                .horarios(List.of(HorarioEnum.H08, HorarioEnum.H20))
                .build();

        TratamentoEntity entity = tratamentoMapper.mapToEntity(tratamento);

        assertThat(entity).isNotNull();
        assertThat(entity.getIdTratamento()).isEqualTo(10);
        assertThat(entity.getIdPaciente()).isEqualTo(99);
        assertThat(entity.getMedicamentos()).hasSize(1);
        assertThat(entity.getMedicamentos().get(0)).isEqualTo(medicacaoEntity);
        assertThat(entity.getInicioTratamento()).isEqualTo(tratamento.getInicioTratamento());
        assertThat(entity.getFimTratamento()).isEqualTo(tratamento.getFimTratamento());
        assertThat(entity.getPeriodicidade()).isEqualTo(tratamento.getPeriodicidade());
        assertThat(entity.getDosagem()).isEqualTo(tratamento.getDosagem());
        assertThat(entity.getHorarios()).containsExactly(HorarioEnum.H08, HorarioEnum.H20);

        verify(medicacaoMapper, times(1)).mapMedicamentoToEntity(medicacao);
    }

    @Test
    void mapToEntity_deveRetornarNullMedicamentosQuandoNulo() {
        Tratamento tratamento = Tratamento.builder()
                .idTratamento(10)
                .idPaciente(88)
                .medicamentos(null)
                .inicioTratamento(LocalDate.now())
                .fimTratamento(LocalDate.now().plusDays(5))
                .periodicidade("Diário")
                .dosagem("100mg")
                .horarios(List.of(HorarioEnum.H14))
                .build();

        TratamentoEntity entity = tratamentoMapper.mapToEntity(tratamento);

        assertThat(entity).isNotNull();
        assertThat(entity.getIdPaciente()).isEqualTo(88);
        assertThat(entity.getMedicamentos()).isNull();
        assertThat(entity.getHorarios()).containsExactly(HorarioEnum.H14);
    }

    @Test
    void mapToDomain_deveConverterCorretamente() {
        MedicacaoEntity medicacaoEntity = MedicacaoEntity.builder()
                .idMedicacao(1)
                .nomeMedicacao("Ibuprofeno")
                .descricaoMedicacao("Para dor")
                .sigtapMedicacao("654321")
                .build();

        Medicacao medicacao = Medicacao.builder()
                .idMedicacao(1)
                .nomeMedicacao("Ibuprofeno")
                .descricaoMedicacao("Para dor")
                .sigtapMedicacao("654321")
                .build();

        when(medicacaoMapper.mapEntityToMedicamento(medicacaoEntity)).thenReturn(medicacao);

        TratamentoEntity entity = TratamentoEntity.builder()
                .idTratamento(20)
                .idPaciente(77)
                .medicamentos(List.of(medicacaoEntity))
                .inicioTratamento(LocalDate.of(2025, 6, 1))
                .fimTratamento(LocalDate.of(2025, 6, 10))
                .periodicidade("3 vezes ao dia")
                .dosagem("250mg")
                .horarios(List.of(HorarioEnum.H00))
                .build();

        Tratamento tratamento = tratamentoMapper.mapToDomain(entity);

        assertThat(tratamento).isNotNull();
        assertThat(tratamento.getIdTratamento()).isEqualTo(20);
        assertThat(tratamento.getIdPaciente()).isEqualTo(77);
        assertThat(tratamento.getMedicamentos()).hasSize(1);
        assertThat(tratamento.getMedicamentos().get(0)).isEqualTo(medicacao);
        assertThat(tratamento.getInicioTratamento()).isEqualTo(entity.getInicioTratamento());
        assertThat(tratamento.getFimTratamento()).isEqualTo(entity.getFimTratamento());
        assertThat(tratamento.getPeriodicidade()).isEqualTo(entity.getPeriodicidade());
        assertThat(tratamento.getDosagem()).isEqualTo(entity.getDosagem());
        assertThat(tratamento.getHorarios()).containsExactly(HorarioEnum.H00);

        verify(medicacaoMapper, times(1)).mapEntityToMedicamento(medicacaoEntity);
    }

    @Test
    void mapToDomain_deveRetornarNullMedicamentosQuandoNulo() {
        TratamentoEntity entity = TratamentoEntity.builder()
                .idTratamento(30)
                .idPaciente(55)
                .medicamentos(null)
                .inicioTratamento(LocalDate.now())
                .fimTratamento(LocalDate.now().plusDays(7))
                .periodicidade("Semanal")
                .dosagem("50mg")
                .horarios(List.of(HorarioEnum.H15, HorarioEnum.H21))
                .build();

        Tratamento tratamento = tratamentoMapper.mapToDomain(entity);

        assertThat(tratamento).isNotNull();
        assertThat(tratamento.getIdPaciente()).isEqualTo(55);
        assertThat(tratamento.getMedicamentos()).isNull();
        assertThat(tratamento.getHorarios()).containsExactly(HorarioEnum.H15, HorarioEnum.H21);
    }

    @Test
    void mapToEntity_deveAceitarHorariosNulos() {
        Tratamento tratamento = Tratamento.builder()
                .idTratamento(40)
                .idPaciente(101)
                .medicamentos(null)
                .inicioTratamento(LocalDate.now())
                .fimTratamento(LocalDate.now().plusDays(2))
                .periodicidade("Diária")
                .dosagem("150mg")
                .horarios(null)
                .build();

        TratamentoEntity entity = tratamentoMapper.mapToEntity(tratamento);

        assertThat(entity).isNotNull();
        assertThat(entity.getHorarios()).isNull();
    }

    @Test
    void mapToDomain_deveAceitarHorariosNulos() {
        TratamentoEntity entity = TratamentoEntity.builder()
                .idTratamento(50)
                .idPaciente(202)
                .medicamentos(null)
                .inicioTratamento(LocalDate.now())
                .fimTratamento(LocalDate.now().plusDays(5))
                .periodicidade("Mensal")
                .dosagem("200mg")
                .horarios(null)
                .build();

        Tratamento tratamento = tratamentoMapper.mapToDomain(entity);

        assertThat(tratamento).isNotNull();
        assertThat(tratamento.getHorarios()).isNull();
    }
}
