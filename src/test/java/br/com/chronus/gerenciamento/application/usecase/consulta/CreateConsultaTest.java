package br.com.chronus.gerenciamento.application.usecase.consulta;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ConsultaExistenteException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateConsultaTest {

    @Mock
    private ConsultaGateway consultaGateway;

    @Mock
    private PacienteGateway pacienteGateway;

    @Mock
    private ProfissionalSaudeGateway profissionalSaudeGateway;

    @InjectMocks
    private CreateConsulta useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarConsultaComSucesso() {
        Consulta request = Consulta.builder()
                .idConsulta(null)  // nova consulta sem id
                .idPaciente(1)
                .idProfissionalSaude(2)
                .dataHoraConsulta(LocalDate.now().plusDays(1))
                .observacaoConsulta("Observação")
                .statusConsulta(null)
                .tipoConsulta(null)
                .motivoCancelamento(null)
                .build();

        when(consultaGateway.getConsultaById(anyInt())).thenReturn(Optional.empty());  // não vai ser chamado, mas para segurança
        when(pacienteGateway.verificaPacientePorId(1)).thenReturn(true);
        when(profissionalSaudeGateway.verificaProfissionalPorId(2)).thenReturn(true);
        when(consultaGateway.createConsulta(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Consulta resultado = useCase.execute(request);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdPaciente());
        assertEquals(2, resultado.getIdProfissionalSaude());
        verify(consultaGateway).createConsulta(any());
    }

    @Test
    void deveLancarExcecaoQuandoConsultaExistente() {
        Consulta request = Consulta.builder()
                .idConsulta(100)
                .dataHoraConsulta(LocalDate.now())
                .build();

        when(consultaGateway.getConsultaById(100)).thenReturn(Optional.of(new Consulta()));

        ConsultaExistenteException exception = assertThrows(
                ConsultaExistenteException.class,
                () -> useCase.execute(request)
        );

        assertTrue(exception.getMessage().contains("100"));
        verify(consultaGateway, never()).createConsulta(any());
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoExiste() {
        Consulta request = Consulta.builder()
                .idConsulta(null)
                .idPaciente(10)
                .dataHoraConsulta(LocalDate.now())
                .build();

        when(consultaGateway.getConsultaById(anyInt())).thenReturn(Optional.empty());
        when(pacienteGateway.verificaPacientePorId(10)).thenReturn(false);

        PacienteNaoEncontradoException exception = assertThrows(
                PacienteNaoEncontradoException.class,
                () -> useCase.execute(request)
        );

        assertTrue(exception.getMessage().contains("10"));
        verify(consultaGateway, never()).createConsulta(any());
    }

    @Test
    void deveLancarExcecaoQuandoProfissionalNaoExiste() {
        Consulta request = Consulta.builder()
                .idConsulta(null)
                .idPaciente(1)
                .idProfissionalSaude(20)
                .dataHoraConsulta(LocalDate.now())
                .build();

        when(consultaGateway.getConsultaById(anyInt())).thenReturn(Optional.empty());
        when(pacienteGateway.verificaPacientePorId(1)).thenReturn(true);
        when(profissionalSaudeGateway.verificaProfissionalPorId(20)).thenReturn(false);

        ProfissionalSaudeNaoEncontradoException exception = assertThrows(
                ProfissionalSaudeNaoEncontradoException.class,
                () -> useCase.execute(request)
        );

        assertTrue(exception.getMessage().contains("20"));
        verify(consultaGateway, never()).createConsulta(any());
    }
}
