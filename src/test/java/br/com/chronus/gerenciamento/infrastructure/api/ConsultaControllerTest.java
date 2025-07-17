package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.dto.consulta.ConsultaRequest;
import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.mapper.ConsultaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConsultaControllerTest {

    @Mock
    private ConsultaGateway consultaGateway;

    @Mock
    private ConsultaMapper consultaMapper;

    @InjectMocks
    private ConsultaController consultaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private ConsultaRequest mockConsultaRequest() {
        ConsultaRequest request = new ConsultaRequest();
        request.setIdPaciente(1);
        request.setIdProfissionalSaude(2);
        request.setDataHoraConsulta(LocalDate.now());
        request.setObservacaoConsulta("Consulta de rotina");
        request.setStatusConsulta(EnumStatusConsulta.PENDENTE);
        request.setTipoConsulta(EnumTipoConsulta.PRESENCIAL);
        return request;
    }

    private Consulta mockConsulta() {
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(1);
        consulta.setIdPaciente(1);
        consulta.setIdProfissionalSaude(2);
        consulta.setDataHoraConsulta(LocalDate.now());
        consulta.setObservacaoConsulta("Consulta de rotina");
        consulta.setStatusConsulta(EnumStatusConsulta.PENDENTE);
        consulta.setTipoConsulta(EnumTipoConsulta.PRESENCIAL);
        return consulta;
    }

    @Test
    void testCreateConsulta() {
        ConsultaRequest request = mockConsultaRequest();
        Consulta consulta = mockConsulta();

        when(consultaMapper.toDomain(request, null)).thenReturn(consulta);
        when(consultaGateway.createConsulta(consulta)).thenReturn(consulta);

        ResponseEntity<Consulta> response = consultaController.createConsulta(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(consulta, response.getBody());
        verify(consultaGateway).createConsulta(consulta);
    }

    @Test
    void testGetConsultaByIdFound() {
        Consulta consulta = mockConsulta();

        when(consultaGateway.getConsultaById(1)).thenReturn(Optional.of(consulta));

        ResponseEntity<Consulta> response = consultaController.getConsultaById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(consulta, response.getBody());
        verify(consultaGateway).getConsultaById(1);
    }

    @Test
    void testGetConsultaByIdNotFound() {
        when(consultaGateway.getConsultaById(1)).thenReturn(Optional.empty());

        ResponseEntity<Consulta> response = consultaController.getConsultaById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(consultaGateway).getConsultaById(1);
    }

    @Test
    void testUpdateConsulta() {
        ConsultaRequest request = mockConsultaRequest();
        Consulta consulta = mockConsulta();
        request.setIdConsulta(1);

        when(consultaMapper.toDomain(request, 1)).thenReturn(consulta);
        when(consultaGateway.updateConsulta(consulta)).thenReturn(consulta);

        ResponseEntity<Consulta> response = consultaController.updateConsulta(1, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(consulta, response.getBody());
        verify(consultaGateway).updateConsulta(consulta);
    }

    @Test
    void testDeleteConsulta() {
        doNothing().when(consultaGateway).deleteConsulta(1);

        ResponseEntity<Void> response = consultaController.deleteConsulta(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(consultaGateway).deleteConsulta(1);
    }
}