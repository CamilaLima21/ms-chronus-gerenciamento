package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.*;
import br.com.chronus.gerenciamento.application.dto.historico.HistoricoRequestDto;
import br.com.chronus.gerenciamento.application.usecase.checkup.FindCheckUpSaudeById;
import br.com.chronus.gerenciamento.application.usecase.consulta.GetConsulta;
import br.com.chronus.gerenciamento.application.usecase.enfermidade.GetEnfermidade;
import br.com.chronus.gerenciamento.application.usecase.tratamento.GetTratamentoByIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HistoricoMapperTest {

    private GetEnfermidade getEnfermidade;
    private GetTratamentoByIdUseCase getTratamento;
    private GetConsulta getConsulta;
    private FindCheckUpSaudeById getCheckUpSaude;
    private HistoricoMapper mapper;

    @BeforeEach
    void setUp() {
        getEnfermidade = mock(GetEnfermidade.class);
        getTratamento = mock(GetTratamentoByIdUseCase.class);
        getConsulta = mock(GetConsulta.class);
        getCheckUpSaude = mock(FindCheckUpSaudeById.class);
        mapper = new HistoricoMapper(getEnfermidade, getTratamento, getConsulta, getCheckUpSaude);
    }

    @Test
    void deveMapearHistoricoCorretamente() {
        // Arrange
        HistoricoRequestDto dto = new HistoricoRequestDto();
        dto.setIdPaciente(1);
        dto.setIdEnfermidades(List.of(100));
        dto.setIdTratamentos(List.of(200));
        dto.setIdConsultas(List.of(300));
        dto.setIdCheckups(List.of(400));
        dto.setObservacoes("Paciente apresenta melhoras significativas");
        dto.setDataInicio(LocalDate.of(2023, 1, 1));
        dto.setDataFim(LocalDate.of(2023, 6, 30));

        Enfermidade enfermidade = new Enfermidade();
        enfermidade.setIdEnfermidade(100);
        Tratamento tratamento = new Tratamento();
        tratamento.setIdTratamento(200);
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(300);
        CheckUpSaude checkup = new CheckUpSaude();
        checkup.setIdCheckUpsaude(400);

        when(getEnfermidade.execute(100)).thenReturn(enfermidade);
        when(getTratamento.execute(200)).thenReturn(Optional.of(tratamento));
        when(getConsulta.execute(300)).thenReturn(consulta);
        when(getCheckUpSaude.execute(400)).thenReturn(checkup);

        Historico historico = mapper.toDomain(dto);

        assertNotNull(historico);
        assertEquals(1, historico.getIdPaciente());
        assertEquals(1, historico.getEnfermidades().size());
        assertEquals(1, historico.getTratamentos().size());
        assertEquals(1, historico.getConsultas().size());
        assertEquals(1, historico.getCheckups().size());
        assertEquals("Paciente apresenta melhoras significativas", historico.getObservacoes());
        assertEquals(LocalDate.of(2023, 1, 1), historico.getDataInicio());
        assertEquals(LocalDate.of(2023, 6, 30), historico.getDataFim());

        verify(getEnfermidade).execute(100);
        verify(getTratamento).execute(200);
        verify(getConsulta).execute(300);
        verify(getCheckUpSaude).execute(400);
    }

    @Test
    void deveRetornarNullQuandoDtoForNull() {
        assertNull(mapper.toDomain(null));
    }
}
