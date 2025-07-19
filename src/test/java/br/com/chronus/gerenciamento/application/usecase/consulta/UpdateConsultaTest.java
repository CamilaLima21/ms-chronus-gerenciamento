package br.com.chronus.gerenciamento.application.usecase.consulta;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.dto.consulta.ConsultaRequest;
import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ConsultaNaoEncontradaException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateConsultaTest {

    @Mock
    private ConsultaGateway consultaGateway;

    @Mock
    private ProfissionalSaudeGateway profissionalSaudeGateway;

    @InjectMocks
    private UpdateConsulta updateConsulta;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarConsultaComSucesso() {
        Integer idConsulta = 1;

        Consulta consultaExistente = Consulta.builder()
                .idConsulta(idConsulta)
                .idProfissionalSaude(10)
                .dataHoraConsulta(LocalDateTime.now())
                .statusConsulta(EnumStatusConsulta.PENDENTE)
                .tipoConsulta(EnumTipoConsulta.TELEMEDICINA)
                .build();

        ConsultaRequest updateRequest = new ConsultaRequest();
        updateRequest.setIdProfissionalSaude(20);
        updateRequest.setDataHoraConsulta(LocalDateTime.now().plusDays(1));
        updateRequest.setStatusConsulta(EnumStatusConsulta.CONFIRMADA);
        updateRequest.setTipoConsulta(EnumTipoConsulta.PRESENCIAL);

        when(consultaGateway.getConsultaById(idConsulta)).thenReturn(java.util.Optional.of(consultaExistente));
        when(profissionalSaudeGateway.verificaProfissionalPorId(20)).thenReturn(true);
        when(consultaGateway.updateConsulta(any(Consulta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Consulta resultado = updateConsulta.execute(idConsulta, updateRequest);

        assertNotNull(resultado);
        assertEquals(20, resultado.getIdProfissionalSaude());
        assertEquals(updateRequest.getDataHoraConsulta(), resultado.getDataHoraConsulta());
        assertEquals(EnumStatusConsulta.CONFIRMADA, resultado.getStatusConsulta());
        assertEquals(EnumTipoConsulta.PRESENCIAL, resultado.getTipoConsulta());

        verify(consultaGateway).updateConsulta(any(Consulta.class));
    }

    @Test
    void deveLancarExcecaoQuandoConsultaNaoEncontrada() {
        Integer idConsulta = 1;
        ConsultaRequest updateRequest = new ConsultaRequest();

        when(consultaGateway.getConsultaById(idConsulta)).thenReturn(java.util.Optional.empty());

        ConsultaNaoEncontradaException exception = assertThrows(
                ConsultaNaoEncontradaException.class,
                () -> updateConsulta.execute(idConsulta, updateRequest)
        );

        assertEquals("Consulta com id [1] não encontrada.", exception.getMessage());
        verify(consultaGateway, never()).updateConsulta(any());
    }

    @Test
    void deveLancarExcecaoQuandoProfissionalNaoExiste() {
        Integer idConsulta = 1;

        Consulta consultaExistente = Consulta.builder()
                .idConsulta(idConsulta)
                .idProfissionalSaude(10)
                .dataHoraConsulta(LocalDateTime.now())
                .statusConsulta(EnumStatusConsulta.PENDENTE)
                .tipoConsulta(EnumTipoConsulta.TELEMEDICINA)
                .build();

        ConsultaRequest updateRequest = new ConsultaRequest();
        updateRequest.setIdProfissionalSaude(999); // profissional inválido

        when(consultaGateway.getConsultaById(idConsulta)).thenReturn(java.util.Optional.of(consultaExistente));
        when(profissionalSaudeGateway.verificaProfissionalPorId(999)).thenReturn(false);

        ProfissionalSaudeNaoEncontradoException exception = assertThrows(
                ProfissionalSaudeNaoEncontradoException.class,
                () -> updateConsulta.execute(idConsulta, updateRequest)
        );

        assertEquals("Profissional de saúde com ID 999 não encontrado.", exception.getMessage());
        verify(consultaGateway, never()).updateConsulta(any());
    }

    @Test
    void deveAtualizarConsultaQuandoProfissionalNaoInformado() {
        Integer idConsulta = 1;

        Consulta consultaExistente = Consulta.builder()
                .idConsulta(idConsulta)
                .idProfissionalSaude(10)
                .dataHoraConsulta(LocalDateTime.now())
                .statusConsulta(EnumStatusConsulta.PENDENTE)
                .tipoConsulta(EnumTipoConsulta.TELEMEDICINA)
                .build();

        ConsultaRequest updateRequest = new ConsultaRequest();
        updateRequest.setIdProfissionalSaude(null);  // profissional não informado
        updateRequest.setDataHoraConsulta(LocalDateTime.now().plusDays(1));
        updateRequest.setStatusConsulta(EnumStatusConsulta.CANCELADA);
        updateRequest.setTipoConsulta(EnumTipoConsulta.PRESENCIAL);

        when(consultaGateway.getConsultaById(idConsulta)).thenReturn(java.util.Optional.of(consultaExistente));
        when(consultaGateway.updateConsulta(any(Consulta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Consulta resultado = updateConsulta.execute(idConsulta, updateRequest);

        assertNotNull(resultado);
        assertNull(resultado.getIdProfissionalSaude());
        assertEquals(updateRequest.getDataHoraConsulta(), resultado.getDataHoraConsulta());
        assertEquals(EnumStatusConsulta.CANCELADA, resultado.getStatusConsulta());
        assertEquals(EnumTipoConsulta.PRESENCIAL, resultado.getTipoConsulta());

        verify(consultaGateway).updateConsulta(any(Consulta.class));
        verify(profissionalSaudeGateway, never()).verificaProfissionalPorId(anyInt());
    }
}
