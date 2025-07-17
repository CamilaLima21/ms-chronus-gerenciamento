package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConsultaTest {

    @Test
    @DisplayName("Deve criar consulta com dados corretos e status CONFIRMADA")
    void deveCriarConsultaComDadosCorretos() {

        Integer idPaciente = 1;
        Integer idProfissionalSaude = 2;
        LocalDate dataConsulta = LocalDate.of(2025, 7, 16);
        String observacao = "Paciente com dor lombar";
        EnumStatusConsulta status = EnumStatusConsulta.CONFIRMADA;
        EnumTipoConsulta tipo = EnumTipoConsulta.PRESENCIAL;
        String motivoCancelamento = null;

        Consulta consulta = Consulta.createConsulta(
                idPaciente,
                idProfissionalSaude,
                dataConsulta,
                observacao,
                status,
                tipo,
                motivoCancelamento
        );

        assertNull(consulta.getIdConsulta());
        assertEquals(idPaciente, consulta.getIdPaciente());
        assertEquals(idProfissionalSaude, consulta.getIdProfissionalSaude());
        assertEquals(dataConsulta, consulta.getDataHoraConsulta());
        assertEquals(observacao, consulta.getObservacaoConsulta());
        assertEquals(EnumStatusConsulta.CONFIRMADA, consulta.getStatusConsulta());
        assertEquals(EnumTipoConsulta.PRESENCIAL, consulta.getTipoConsulta());
        assertNull(consulta.getMotivoCancelamento());
    }

    @Test
    @DisplayName("Deve permitir todos os tipos de consulta")
    void devePermitirTodosOsTiposDeConsulta() {
        assertEquals("PRESENCIAL", EnumTipoConsulta.PRESENCIAL.name());
        assertEquals("TELEMEDICINA", EnumTipoConsulta.TELEMEDICINA.name());
    }

    @Test
    @DisplayName("Deve permitir todos os status de consulta")
    void devePermitirTodosOsStatusDeConsulta() {
        assertEquals("CONFIRMADA", EnumStatusConsulta.CONFIRMADA.name());
        assertEquals("PENDENTE", EnumStatusConsulta.PENDENTE.name());
        assertEquals("CANCELADA", EnumStatusConsulta.CANCELADA.name());
    }

    @Test
    @DisplayName("Deve criar consulta com status CANCELADA e motivo")
    void deveCriarConsultaCanceladaComMotivo() {

        Integer idPaciente = 10;
        Integer idProfissional = 20;
        LocalDate data = LocalDate.now();
        String observacao = "Consulta cancelada pelo paciente";
        EnumStatusConsulta status = EnumStatusConsulta.CANCELADA;
        EnumTipoConsulta tipo = EnumTipoConsulta.TELEMEDICINA;
        String motivo = "Problemas pessoais";

        Consulta consulta = Consulta.createConsulta(
                idPaciente,
                idProfissional,
                data,
                observacao,
                status,
                tipo,
                motivo
        );

        assertEquals(EnumStatusConsulta.CANCELADA, consulta.getStatusConsulta());
        assertEquals("Problemas pessoais", consulta.getMotivoCancelamento());
    }

    @Test
    @DisplayName("Deve criar consulta com status PENDENTE e sem motivo de cancelamento")
    void deveCriarConsultaPendenteSemMotivoCancelamento() {

        Consulta consulta = Consulta.createConsulta(
                3,
                4,
                LocalDate.of(2025, 8, 1),
                "Aguardando confirmação",
                EnumStatusConsulta.PENDENTE,
                EnumTipoConsulta.PRESENCIAL,
                null
        );

        assertEquals(EnumStatusConsulta.PENDENTE, consulta.getStatusConsulta());
        assertNull(consulta.getMotivoCancelamento());
    }
}
