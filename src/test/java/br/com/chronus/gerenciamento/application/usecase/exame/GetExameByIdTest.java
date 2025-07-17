package br.com.chronus.gerenciamento.application.usecase.exame;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.exame.exception.ExameNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetExameByIdTest {

    @Mock
    private ExameGateway exameGateway;

    @Mock
    private PacienteGateway pacienteGateway;

    @Mock
    private ProfissionalSaudeGateway profissionalSaudeGateway;

    @InjectMocks
    private GetExameById getExameById;

    private Exame exame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        exame = Exame.builder()
                .idExame(1)
                .idPaciente(100)
                .idProfissionalSaude(200)
                .dataExame(LocalDateTime.now())
                .listaExames(List.of(EnumExame.HEMOGLOBINA_GLICADA_DIABETES))
                .statusExame(EnumStatusExame.AGENDADO)
                .build();
    }

    @Test
    void deveRetornarExameQuandoExistirEValidarPacienteEProfissional() {
        when(exameGateway.findExameById(exame.getIdExame())).thenReturn(Optional.of(exame));
        when(pacienteGateway.verificaPacientePorId(exame.getIdPaciente())).thenReturn(true);
        when(profissionalSaudeGateway.verificaProfissionalPorId(exame.getIdProfissionalSaude())).thenReturn(true);

        Exame resultado = getExameById.execute(exame.getIdExame());

        assertNotNull(resultado);
        assertEquals(exame.getIdExame(), resultado.getIdExame());
        verify(exameGateway, times(1)).findExameById(exame.getIdExame());
        verify(pacienteGateway, times(1)).verificaPacientePorId(exame.getIdPaciente());
        verify(profissionalSaudeGateway, times(1)).verificaProfissionalPorId(exame.getIdProfissionalSaude());
    }

    @Test
    void deveLancarExcecaoQuandoExameNaoExistir() {
        when(exameGateway.findExameById(exame.getIdExame())).thenReturn(Optional.empty());

        ExameNaoEncontradoException exception = assertThrows(ExameNaoEncontradoException.class,
                () -> getExameById.execute(exame.getIdExame()));

        assertEquals("Exame com id [1] não encontrado.", exception.getMessage());
        verify(exameGateway, times(1)).findExameById(exame.getIdExame());
        verifyNoMoreInteractions(pacienteGateway, profissionalSaudeGateway);
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoExistir() {
        when(exameGateway.findExameById(exame.getIdExame())).thenReturn(Optional.of(exame));
        when(pacienteGateway.verificaPacientePorId(exame.getIdPaciente())).thenReturn(false);

        PacienteNaoEncontradoException exception = assertThrows(PacienteNaoEncontradoException.class,
                () -> getExameById.execute(exame.getIdExame()));

        assertEquals("Paciente com ID 100 não encontrado.", exception.getMessage());
        verify(exameGateway, times(1)).findExameById(exame.getIdExame());
        verify(pacienteGateway, times(1)).verificaPacientePorId(exame.getIdPaciente());
        verifyNoInteractions(profissionalSaudeGateway);
    }

    @Test
    void deveLancarExcecaoQuandoProfissionalNaoExistir() {
        when(exameGateway.findExameById(exame.getIdExame())).thenReturn(Optional.of(exame));
        when(pacienteGateway.verificaPacientePorId(exame.getIdPaciente())).thenReturn(true);
        when(profissionalSaudeGateway.verificaProfissionalPorId(exame.getIdProfissionalSaude())).thenReturn(false);

        ProfissionalSaudeNaoEncontradoException exception = assertThrows(ProfissionalSaudeNaoEncontradoException.class,
                () -> getExameById.execute(exame.getIdExame()));

        assertEquals("Profissional de saúde com ID 200 não encontrado.", exception.getMessage());
        verify(exameGateway, times(1)).findExameById(exame.getIdExame());
        verify(pacienteGateway, times(1)).verificaPacientePorId(exame.getIdPaciente());
        verify(profissionalSaudeGateway, times(1)).verificaProfissionalPorId(exame.getIdProfissionalSaude());
    }
}
