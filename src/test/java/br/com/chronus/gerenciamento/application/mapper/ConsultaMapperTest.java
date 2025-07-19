package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.dto.consulta.ConsultaRequest;
import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsultaMapperTest {

    private final ConsultaMapper mapper = new ConsultaMapper();

    @Test
    void testToDomain_shouldMapFieldsCorrectly() {
        ConsultaRequest dto = new ConsultaRequest();
        dto.setIdPaciente(100);
        dto.setIdProfissionalSaude(200);
        dto.setDataHoraConsulta(LocalDateTime.of(2025, 7, 17, 10, 30)); // Ajuste caso precise DateTime
        dto.setObservacaoConsulta("Observação de teste");
        dto.setStatusConsulta(EnumStatusConsulta.CONFIRMADA);
        dto.setTipoConsulta(EnumTipoConsulta.PRESENCIAL);
        dto.setMotivoCancelamento(null);

        Integer idConsulta = 10;

        Consulta domain = mapper.toDomain(dto, idConsulta);

        assertNotNull(domain);
        assertEquals(idConsulta, domain.getIdConsulta());
        assertEquals(dto.getIdPaciente(), domain.getIdPaciente());
        assertEquals(dto.getIdProfissionalSaude(), domain.getIdProfissionalSaude());
        assertEquals(dto.getDataHoraConsulta(), domain.getDataHoraConsulta());
        assertEquals(dto.getObservacaoConsulta(), domain.getObservacaoConsulta());
        assertEquals(dto.getStatusConsulta(), domain.getStatusConsulta());
        assertEquals(dto.getTipoConsulta(), domain.getTipoConsulta());
        assertEquals(dto.getMotivoCancelamento(), domain.getMotivoCancelamento());
    }
}
