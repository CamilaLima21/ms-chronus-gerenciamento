package br.com.chronus.gerenciamento.application.usecase.exame;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.dto.exame.ExameRequest;
import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateExameTest {

    @Mock
    private ExameGateway exameGateway;

    @Mock
    private PacienteGateway pacienteGateway;

    @Mock
    private ProfissionalSaudeGateway profissionalSaudeGateway;

    @InjectMocks
    private UpdateExame updateExame;

    private Exame exameExistente;
    private ExameRequest exameRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        exameExistente = Exame.builder()
                .idExame(1)
                .idPaciente(100)
                .idProfissionalSaude(200)
                .dataExame(LocalDateTime.now().minusDays(1))
                .listaExames(List.of(EnumExame.HEMOGLOBINA_GLICADA_DIABETES, EnumExame.GLICEMIA_JEJUM_DIABETES))
                .statusExame(EnumStatusExame.AGENDADO)
                .build();

        exameRequest = new ExameRequest();
        exameRequest.setIdPaciente(101);
        exameRequest.setIdProfissionalSaude(201);
        exameRequest.setDataExame(LocalDateTime.now());
        exameRequest.setListaExames(List.of(EnumExame.PERFIL_LIPIDICO_COLESTEROL, EnumExame.MAMOGRAFIA_CANCERES));
        exameRequest.setStatusExame(EnumStatusExame.CONCLUIDO);


        assertEquals(EnumEnfermidade.DIABETES, EnumExame.HEMOGLOBINA_GLICADA_DIABETES.getEnfermidade());
    }

    @Test
    void deveAtualizarExameComSucesso() {
        when(exameGateway.findExameById(exameExistente.getIdExame())).thenReturn(Optional.of(exameExistente));
        when(pacienteGateway.verificaPacientePorId(exameRequest.getIdPaciente())).thenReturn(true);
        when(profissionalSaudeGateway.verificaProfissionalPorId(exameRequest.getIdProfissionalSaude())).thenReturn(true);
        when(exameGateway.update(any(Exame.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Exame atualizado = updateExame.execute(exameExistente.getIdExame(), exameRequest);

        assertNotNull(atualizado);
        assertEquals(exameRequest.getIdPaciente(), atualizado.getIdPaciente());
        assertEquals(exameRequest.getIdProfissionalSaude(), atualizado.getIdProfissionalSaude());
        assertEquals(exameRequest.getDataExame(), atualizado.getDataExame());
        assertEquals(exameRequest.getListaExames(), atualizado.getListaExames());
        assertEquals(exameRequest.getStatusExame(), atualizado.getStatusExame());

        verify(exameGateway).findExameById(exameExistente.getIdExame());
        verify(pacienteGateway).verificaPacientePorId(exameRequest.getIdPaciente());
        verify(profissionalSaudeGateway).verificaProfissionalPorId(exameRequest.getIdProfissionalSaude());
        verify(exameGateway).update(any(Exame.class));
    }

    @Test
    void deveLancarExcecaoQuandoExameNaoEncontrado() {
        when(exameGateway.findExameById(anyInt())).thenReturn(Optional.empty());

        ExameNaoEncontradoException exception = assertThrows(ExameNaoEncontradoException.class,
                () -> updateExame.execute(999, exameRequest));

        assertEquals("Exame com id [999] não encontrado.", exception.getMessage());

        verify(exameGateway).findExameById(999);
        verifyNoMoreInteractions(pacienteGateway, profissionalSaudeGateway, exameGateway);
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoEncontrado() {
        when(exameGateway.findExameById(exameExistente.getIdExame())).thenReturn(Optional.of(exameExistente));
        when(pacienteGateway.verificaPacientePorId(exameRequest.getIdPaciente())).thenReturn(false);

        PacienteNaoEncontradoException exception = assertThrows(PacienteNaoEncontradoException.class,
                () -> updateExame.execute(exameExistente.getIdExame(), exameRequest));

        assertEquals("Paciente com ID 101 não encontrado.", exception.getMessage());

        verify(exameGateway).findExameById(exameExistente.getIdExame());
        verify(pacienteGateway).verificaPacientePorId(exameRequest.getIdPaciente());
        verifyNoMoreInteractions(profissionalSaudeGateway, exameGateway);
    }


    @Test
    void deveLancarExcecaoQuandoProfissionalNaoEncontrado() {
        when(exameGateway.findExameById(exameExistente.getIdExame())).thenReturn(Optional.of(exameExistente));
        when(pacienteGateway.verificaPacientePorId(exameRequest.getIdPaciente())).thenReturn(true);
        when(profissionalSaudeGateway.verificaProfissionalPorId(exameRequest.getIdProfissionalSaude())).thenReturn(false);

        ProfissionalSaudeNaoEncontradoException exception = assertThrows(ProfissionalSaudeNaoEncontradoException.class,
                () -> updateExame.execute(exameExistente.getIdExame(), exameRequest));

        assertEquals("Profissional de saúde com ID 201 não encontrado.", exception.getMessage());

        verify(exameGateway).findExameById(exameExistente.getIdExame());
        verify(pacienteGateway).verificaPacientePorId(exameRequest.getIdPaciente());
        verify(profissionalSaudeGateway).verificaProfissionalPorId(exameRequest.getIdProfissionalSaude());
        verifyNoMoreInteractions(exameGateway);
    }
}
