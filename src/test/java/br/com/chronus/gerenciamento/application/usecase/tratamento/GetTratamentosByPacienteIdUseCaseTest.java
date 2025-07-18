package br.com.chronus.gerenciamento.application.usecase.tratamento;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetTratamentosByPacienteIdUseCaseTest {

    private TratamentoGateway tratamentoGateway;
    private GetTratamentosByPacienteIdUseCase getTratamentosByPacienteIdUseCase;

    @BeforeEach
    void setUp() {
        tratamentoGateway = mock(TratamentoGateway.class);
        getTratamentosByPacienteIdUseCase = new GetTratamentosByPacienteIdUseCase(tratamentoGateway);
    }

    @Test
    void deveRetornarListaDeTratamentosParaPaciente() {

        Integer pacienteId = 1;

        List<Medicacao> medicacoes = List.of(Medicacao.builder().idMedicacao(1).nomeMedicacao("Dipirona").build());
        List<HorarioEnum> horarios = List.of(HorarioEnum.H08, HorarioEnum.H20);

        Tratamento tratamento = Tratamento.builder()
                .idTratamento(10)
                .idPaciente(pacienteId)
                .medicamentos(medicacoes)
                .inicioTratamento(LocalDate.of(2024, 1, 1))
                .fimTratamento(LocalDate.of(2024, 1, 10))
                .periodicidade("Diária")
                .dosagem("500mg")
                .horarios(horarios)
                .build();

        List<Tratamento> tratamentos = List.of(tratamento);

        when(tratamentoGateway.findByPacienteId(pacienteId)).thenReturn(tratamentos);

        List<Tratamento> resultado = getTratamentosByPacienteIdUseCase.execute(pacienteId);

        assertEquals(1, resultado.size());
        assertEquals(tratamento, resultado.get(0));
        verify(tratamentoGateway, times(1)).findByPacienteId(pacienteId);
    }

    @Test
    void deveRetornarListaVaziaQuandoNenhumTratamentoEncontrado() {

        Integer pacienteId = 2;
        when(tratamentoGateway.findByPacienteId(pacienteId)).thenReturn(Collections.emptyList());

        List<Tratamento> resultado = getTratamentosByPacienteIdUseCase.execute(pacienteId);

        assertEquals(0, resultado.size());
        verify(tratamentoGateway, times(1)).findByPacienteId(pacienteId);
    }
}
