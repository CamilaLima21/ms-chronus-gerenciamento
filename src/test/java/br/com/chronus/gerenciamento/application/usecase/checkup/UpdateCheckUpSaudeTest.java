package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.dto.checkup.CheckUpSaudeRequest;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.checkup.exception.CheckUpSaudeNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateCheckUpSaudeTest {

    private CheckUpSaudeGateway checkUpSaudeGateway;
    private PacienteGateway pacienteGateway;
    private ProfissionalSaudeGateway profissionalSaudeGateway;
    private UpdateCheckUpSaude updateCheckUpSaude;

    @BeforeEach
    void setUp() {
        checkUpSaudeGateway = mock(CheckUpSaudeGateway.class);
        pacienteGateway = mock(PacienteGateway.class);
        profissionalSaudeGateway = mock(ProfissionalSaudeGateway.class);
        updateCheckUpSaude = new UpdateCheckUpSaude(checkUpSaudeGateway, pacienteGateway, profissionalSaudeGateway);
    }

    @Test
    void deveAtualizarCheckUpComSucesso() {
        Integer idCheckUp = 1;
        Integer idPaciente = 10;
        Integer idProfissional = 20;

        CheckUpSaude checkUpExistente = CheckUpSaude.builder()
                .idCheckUpsaude(idCheckUp)
                .idPaciente(5)
                .idProfissionalSaude(15)
                .build();

        CheckUpSaudeRequest request = new CheckUpSaudeRequest();
        request.setIdPaciente(idPaciente);
        request.setIdProfissionalSaude(idProfissional);
        request.setGlicemia("90");
        request.setPressaoArterial("120/80");
        request.setFrequenciaCardiaca("70");
        request.setFrequenciaRespiratoria("18");
        request.setTemperaturaCorporal("36.5");
        request.setSaturacaoOxigenio("98%");
        request.setOutrosDados("Exemplo");
        request.setObservacoes("Tudo certo");

        when(checkUpSaudeGateway.findById(idCheckUp)).thenReturn(Optional.of(checkUpExistente));
        when(pacienteGateway.verificaPacientePorId(idPaciente)).thenReturn(true);
        when(profissionalSaudeGateway.verificaProfissionalPorId(idProfissional)).thenReturn(true);
        when(checkUpSaudeGateway.update(any())).thenAnswer(invocation -> invocation.getArgument(0));

        CheckUpSaude atualizado = updateCheckUpSaude.execute(idCheckUp, request);

        assertThat(atualizado.getIdPaciente()).isEqualTo(idPaciente);
        assertThat(atualizado.getIdProfissionalSaude()).isEqualTo(idProfissional);
        assertThat(atualizado.getGlicemia()).isEqualTo("90");
        assertThat(atualizado.getPressaoArterial()).isEqualTo("120/80");
        assertThat(atualizado.getObservacoes()).isEqualTo("Tudo certo");

        verify(checkUpSaudeGateway).update(checkUpExistente);
    }

    @Test
    void deveLancarExcecaoQuandoCheckUpNaoEncontrado() {
        Integer idCheckUp = 1;
        CheckUpSaudeRequest request = new CheckUpSaudeRequest();
        request.setIdPaciente(10);

        when(checkUpSaudeGateway.findById(idCheckUp)).thenReturn(Optional.empty());

        assertThrows(CheckUpSaudeNaoEncontradoException.class, () ->
                updateCheckUpSaude.execute(idCheckUp, request));
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoExiste() {
        Integer idCheckUp = 1;
        Integer idPaciente = 10;

        CheckUpSaude checkUpExistente = new CheckUpSaude();
        CheckUpSaudeRequest request = new CheckUpSaudeRequest();
        request.setIdPaciente(idPaciente);

        when(checkUpSaudeGateway.findById(idCheckUp)).thenReturn(Optional.of(checkUpExistente));
        when(pacienteGateway.verificaPacientePorId(idPaciente)).thenReturn(false);

        assertThrows(PacienteNaoEncontradoException.class, () ->
                updateCheckUpSaude.execute(idCheckUp, request));
    }

    @Test
    void deveLancarExcecaoQuandoProfissionalNaoExiste() {
        Integer idCheckUp = 1;
        Integer idPaciente = 10;
        Integer idProfissional = 20;

        CheckUpSaude checkUpExistente = new CheckUpSaude();
        CheckUpSaudeRequest request = new CheckUpSaudeRequest();
        request.setIdPaciente(idPaciente);
        request.setIdProfissionalSaude(idProfissional);

        when(checkUpSaudeGateway.findById(idCheckUp)).thenReturn(Optional.of(checkUpExistente));
        when(pacienteGateway.verificaPacientePorId(idPaciente)).thenReturn(true);
        when(profissionalSaudeGateway.verificaProfissionalPorId(idProfissional)).thenReturn(false);

        assertThrows(ProfissionalSaudeNaoEncontradoException.class, () ->
                updateCheckUpSaude.execute(idCheckUp, request));
    }
}
