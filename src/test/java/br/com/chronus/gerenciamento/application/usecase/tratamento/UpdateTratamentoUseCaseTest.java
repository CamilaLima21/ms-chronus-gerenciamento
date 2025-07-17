package br.com.chronus.gerenciamento.application.usecase.tratamento;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UpdateTratamentoUseCaseTest {

    private TratamentoGateway tratamentoGateway;
    private UpdateTratamentoUseCase updateTratamentoUseCase;

    @BeforeEach
    void setUp() {
        tratamentoGateway = mock(TratamentoGateway.class);
        updateTratamentoUseCase = new UpdateTratamentoUseCase(tratamentoGateway);
    }

    @Test
    void deveAtualizarTratamentoComSucesso() {
        Medicacao medicacao = new Medicacao();
        medicacao.setIdMedicacao(1);
        medicacao.setNomeMedicacao("Dipirona");

        List<Medicacao> medicamentos = List.of(medicacao);
        List<HorarioEnum> horarios = List.of(HorarioEnum.H08, HorarioEnum.H20); // 8h e 20h

        Tratamento tratamento = Tratamento.builder()
                .idTratamento(1)
                .medicamentos(medicamentos)
                .inicioTratamento(LocalDate.of(2023, 1, 1))
                .fimTratamento(LocalDate.of(2023, 1, 10))
                .periodicidade("Diária")
                .dosagem("500mg")
                .horarios(horarios)
                .build();

        Tratamento tratamentoAtualizado = Tratamento.builder()
                .idTratamento(1)
                .medicamentos(medicamentos)
                .inicioTratamento(LocalDate.of(2023, 1, 1))
                .fimTratamento(LocalDate.of(2023, 1, 15))
                .periodicidade("Diária")
                .dosagem("750mg")
                .horarios(horarios)
                .build();

        when(tratamentoGateway.update(tratamento)).thenReturn(tratamentoAtualizado);

        Tratamento resultado = updateTratamentoUseCase.execute(tratamento);

        assertNotNull(resultado);
        assertEquals(tratamento.getIdTratamento(), resultado.getIdTratamento());
        assertEquals("750mg", resultado.getDosagem());
        assertEquals(LocalDate.of(2023, 1, 15), resultado.getFimTratamento());
        verify(tratamentoGateway, times(1)).update(tratamento);
    }
}
