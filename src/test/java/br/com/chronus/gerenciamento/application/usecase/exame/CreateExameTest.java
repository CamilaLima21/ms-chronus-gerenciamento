package br.com.chronus.gerenciamento.application.usecase.exame;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.exame.exception.ExameExistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateExameTest {

    @Mock
    private ExameGateway exameGateway;

    @Mock
    private PacienteGateway pacienteGateway;

    @Mock
    private ProfissionalSaudeGateway profissionalSaudeGateway;

    @InjectMocks
    private CreateExame createExame;

    private Exame exame;

    @BeforeEach
    void setUp() {
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
    void deveCriarExameComSucesso() {
        when(exameGateway.findExameById(exame.getIdExame())).thenReturn(Optional.empty());
        when(pacienteGateway.verificaPacientePorId(exame.getIdPaciente())).thenReturn(true);
        when(profissionalSaudeGateway.verificaProfissionalPorId(exame.getIdProfissionalSaude())).thenReturn(true);
        when(exameGateway.save(any())).thenAnswer(invocation -> {
            Exame arg = invocation.getArgument(0);
            arg.setIdExame(1);
            return arg;
        });

        Exame created = createExame.execute(exame);

        assertNotNull(created);
        assertEquals(exame.getIdPaciente(), created.getIdPaciente());
        assertEquals(exame.getIdProfissionalSaude(), created.getIdProfissionalSaude());
        assertEquals(exame.getListaExames(), created.getListaExames());
        assertEquals(EnumStatusExame.AGENDADO, created.getStatusExame());
        verify(exameGateway).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoExameJaExiste() {
        when(exameGateway.findExameById(exame.getIdExame())).thenReturn(Optional.of(exame));

        ExameExistenteException exception = assertThrows(ExameExistenteException.class,
                () -> createExame.execute(exame));

        assertEquals("Exame com id [1] já existe.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoExiste() {
        when(exameGateway.findExameById(exame.getIdExame())).thenReturn(Optional.empty());
        when(pacienteGateway.verificaPacientePorId(exame.getIdPaciente())).thenReturn(false);

        PacienteNaoEncontradoException exception = assertThrows(PacienteNaoEncontradoException.class,
                () -> createExame.execute(exame));

        assertEquals("Paciente com ID 100 não encontrado.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoProfissionalNaoExiste() {
        when(exameGateway.findExameById(exame.getIdExame())).thenReturn(Optional.empty());
        when(pacienteGateway.verificaPacientePorId(exame.getIdPaciente())).thenReturn(true);
        when(profissionalSaudeGateway.verificaProfissionalPorId(exame.getIdProfissionalSaude())).thenReturn(false);

        ProfissionalSaudeNaoEncontradoException exception = assertThrows(ProfissionalSaudeNaoEncontradoException.class,
                () -> createExame.execute(exame));

        assertEquals("Profissional de saúde com ID 200 não encontrado.", exception.getMessage());
    }

}
