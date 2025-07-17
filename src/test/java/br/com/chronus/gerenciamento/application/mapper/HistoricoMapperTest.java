package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.dto.historico.CreateHistoricoRequestDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HistoricoMapperTest {

    @Test
    void toDomain_shouldMapFieldsCorrectly() {
        CreateHistoricoRequestDto dto = new CreateHistoricoRequestDto();
        dto.setIdPaciente(1);
        dto.setIdEnfermidade(2);
        dto.setIdMedicacao(3);
        dto.setIdTratamento(4);
        dto.setIdConsulta(5);
        dto.setIdCheckup(6);
        dto.setObservacoes("Observação de teste");
        dto.setDataInicio(LocalDate.of(2025, 7, 1));
        dto.setDataFim(LocalDate.of(2025, 7, 15));

        Historico historico = HistoricoMapper.toDomain(dto);

        assertNotNull(historico);
        assertEquals(dto.getIdPaciente(), historico.getIdPaciente());
        assertEquals(dto.getIdEnfermidade(), historico.getEnfermidades());
        assertEquals(dto.getIdMedicacao(), historico.getMedicamentos());
        assertEquals(dto.getIdTratamento(), historico.getTratamento());
        assertEquals(dto.getIdConsulta(), historico.getConsulta());
        assertEquals(dto.getIdCheckup(), historico.getIdCheckup());
        assertEquals(dto.getObservacoes(), historico.getObservacoes());
        assertEquals(dto.getDataInicio(), historico.getDataInicio());
        assertEquals(dto.getDataFim(), historico.getDataFim());
    }

    @Test
    void toDomain_shouldReturnNullIfDtoIsNull() {
        assertNull(HistoricoMapper.toDomain(null));
    }
}
