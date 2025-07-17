package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConsultaEntityTest {

    @Test
    void testBuilderAndGetters() {
        Integer idConsulta = 1;
        Integer idPaciente = 10;
        Integer idProfissional = 20;
        LocalDate dataConsulta = LocalDate.of(2025, 7, 17);
        String observacao = "Consulta de rotina";
        EnumStatusConsulta status = EnumStatusConsulta.CONFIRMADA;
        EnumTipoConsulta tipo = EnumTipoConsulta.PRESENCIAL;
        String motivoCancelamento = "Paciente não compareceu";

        ConsultaEntity consulta = ConsultaEntity.builder()
                .idConsulta(idConsulta)
                .idPaciente(idPaciente)
                .idProfissionalSaude(idProfissional)
                .dataHoraConsulta(dataConsulta)
                .observacaoConsulta(observacao)
                .statusConsulta(status)
                .tipoConsulta(tipo)
                .motivoCancelamento(motivoCancelamento)
                .build();

        assertEquals(idConsulta, consulta.getIdConsulta());
        assertEquals(idPaciente, consulta.getIdPaciente());
        assertEquals(idProfissional, consulta.getIdProfissionalSaude());
        assertEquals(dataConsulta, consulta.getDataHoraConsulta());
        assertEquals(observacao, consulta.getObservacaoConsulta());
        assertEquals(status, consulta.getStatusConsulta());
        assertEquals(tipo, consulta.getTipoConsulta());
        assertEquals(motivoCancelamento, consulta.getMotivoCancelamento());
    }

    @Test
    void testSettersAndToString() {
        ConsultaEntity consulta = new ConsultaEntity();
        consulta.setIdConsulta(2);
        consulta.setIdPaciente(15);
        consulta.setIdProfissionalSaude(25);
        consulta.setDataHoraConsulta(LocalDate.of(2025, 8, 1));
        consulta.setObservacaoConsulta("Acompanhamento");
        consulta.setStatusConsulta(EnumStatusConsulta.PENDENTE);
        consulta.setTipoConsulta(EnumTipoConsulta.TELEMEDICINA);
        consulta.setMotivoCancelamento(null);

        assertEquals(2, consulta.getIdConsulta());
        assertEquals(15, consulta.getIdPaciente());
        assertEquals(25, consulta.getIdProfissionalSaude());
        assertEquals(LocalDate.of(2025, 8, 1), consulta.getDataHoraConsulta());
        assertEquals("Acompanhamento", consulta.getObservacaoConsulta());
        assertEquals(EnumStatusConsulta.PENDENTE, consulta.getStatusConsulta());
        assertEquals(EnumTipoConsulta.TELEMEDICINA, consulta.getTipoConsulta());
        assertNull(consulta.getMotivoCancelamento());
    }
}
