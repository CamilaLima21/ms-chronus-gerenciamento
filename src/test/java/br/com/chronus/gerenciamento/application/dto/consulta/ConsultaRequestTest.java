package br.com.chronus.gerenciamento.application.dto.consulta;

import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsultaRequestTest {

    @Test
    void deveCriarConsultaRequestComTodosOsCampos() {

        Integer idConsulta = 1;
        Integer idPaciente = 100;
        Integer idProfissionalSaude = 200;
        LocalDate dataHoraConsulta = LocalDate.of(2025, 7, 17);
        String observacao = "Consulta de rotina";
        EnumStatusConsulta status = EnumStatusConsulta.CONFIRMADA;
        EnumTipoConsulta tipo = EnumTipoConsulta.TELEMEDICINA;
        String motivoCancelamento = "Paciente não compareceu";

        ConsultaRequest consultaRequest = new ConsultaRequest();
        consultaRequest.setIdConsulta(idConsulta);
        consultaRequest.setIdPaciente(idPaciente);
        consultaRequest.setIdProfissionalSaude(idProfissionalSaude);
        consultaRequest.setDataHoraConsulta(dataHoraConsulta);
        consultaRequest.setObservacaoConsulta(observacao);
        consultaRequest.setStatusConsulta(status);
        consultaRequest.setTipoConsulta(tipo);
        consultaRequest.setMotivoCancelamento(motivoCancelamento);

        assertEquals(idConsulta, consultaRequest.getIdConsulta());
        assertEquals(idPaciente, consultaRequest.getIdPaciente());
        assertEquals(idProfissionalSaude, consultaRequest.getIdProfissionalSaude());
        assertEquals(dataHoraConsulta, consultaRequest.getDataHoraConsulta());
        assertEquals(observacao, consultaRequest.getObservacaoConsulta());
        assertEquals(status, consultaRequest.getStatusConsulta());
        assertEquals(tipo, consultaRequest.getTipoConsulta());
        assertEquals(motivoCancelamento, consultaRequest.getMotivoCancelamento());
    }

    @Test
    void deveAceitarEnumTipoConsultaValido() {
        ConsultaRequest consulta = new ConsultaRequest();
        consulta.setTipoConsulta(EnumTipoConsulta.PRESENCIAL);

        assertEquals(EnumTipoConsulta.PRESENCIAL, consulta.getTipoConsulta());
    }

    @Test
    void deveAceitarEnumStatusConsultaValido() {
        ConsultaRequest consulta = new ConsultaRequest();
        consulta.setStatusConsulta(EnumStatusConsulta.CANCELADA);

        assertEquals(EnumStatusConsulta.CANCELADA, consulta.getStatusConsulta());
    }
}
