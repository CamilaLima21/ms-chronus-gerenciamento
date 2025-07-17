package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.checkup.exception.CheckUpSaudeExistenteException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCheckUpSaudeTest {

    private CheckUpSaudeGateway checkUpGateway;
    private PacienteGateway pacienteGateway;
    private ProfissionalSaudeGateway profissionalGateway;
    private CreateCheckUpSaude createCheckUpSaude;

    @BeforeEach
    void setup() {
        checkUpGateway = mock(CheckUpSaudeGateway.class);
        pacienteGateway = mock(PacienteGateway.class);
        profissionalGateway = mock(ProfissionalSaudeGateway.class);
        createCheckUpSaude = new CreateCheckUpSaude(checkUpGateway, pacienteGateway, profissionalGateway);
    }

    private CheckUpSaude buildCheckUp() {
        return CheckUpSaude.builder()
                .idCheckUpsaude(1)
                .idPaciente(10)
                .idProfissionalSaude(20)
                .glicemia("90")
                .pressaoArterial("120/80")
                .frequenciaCardiaca("70")
                .frequenciaRespiratoria("18")
                .temperaturaCorporal("36.5")
                .saturacaoOxigenio("98%")
                .outrosDados("Nenhum")
                .observacoes("Observação teste")
                .build();
    }

    @Test
    void shouldThrowIfCheckUpAlreadyExists() {
        CheckUpSaude checkUp = buildCheckUp();
        when(checkUpGateway.findById(checkUp.getIdCheckUpsaude())).thenReturn(Optional.of(checkUp));

        CheckUpSaudeExistenteException exception = assertThrows(CheckUpSaudeExistenteException.class, () -> {
            createCheckUpSaude.execute(checkUp);
        });

        assertTrue(exception.getMessage().contains(checkUp.getIdCheckUpsaude().toString()));
        verify(checkUpGateway, never()).save(any());
    }

    @Test
    void shouldThrowIfPacienteNotFound() {
        CheckUpSaude checkUp = buildCheckUp();
        when(checkUpGateway.findById(checkUp.getIdCheckUpsaude())).thenReturn(Optional.empty());
        when(pacienteGateway.verificaPacientePorId(checkUp.getIdPaciente())).thenReturn(false);

        assertThrows(PacienteNaoEncontradoException.class, () -> {
            createCheckUpSaude.execute(checkUp);
        });

        verify(checkUpGateway, never()).save(any());
    }

    @Test
    void shouldThrowIfProfissionalNotFound() {
        CheckUpSaude checkUp = buildCheckUp();
        when(checkUpGateway.findById(checkUp.getIdCheckUpsaude())).thenReturn(Optional.empty());
        when(pacienteGateway.verificaPacientePorId(checkUp.getIdPaciente())).thenReturn(true);
        when(profissionalGateway.verificaProfissionalPorId(checkUp.getIdProfissionalSaude())).thenReturn(false);

        assertThrows(ProfissionalSaudeNaoEncontradoException.class, () -> {
            createCheckUpSaude.execute(checkUp);
        });

        verify(checkUpGateway, never()).save(any());
    }

    @Test
    void shouldSaveAndReturnNewCheckUp() {
        CheckUpSaude checkUp = buildCheckUp();
        when(checkUpGateway.findById(checkUp.getIdCheckUpsaude())).thenReturn(Optional.empty());
        when(pacienteGateway.verificaPacientePorId(checkUp.getIdPaciente())).thenReturn(true);
        when(profissionalGateway.verificaProfissionalPorId(checkUp.getIdProfissionalSaude())).thenReturn(true);

        // Simular que o save retorna o objeto salvo (pode ser o mesmo ou com id gerado)
        when(checkUpGateway.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        CheckUpSaude resultado = createCheckUpSaude.execute(checkUp);

        assertNotNull(resultado);
        assertEquals(checkUp.getIdPaciente(), resultado.getIdPaciente());
        assertEquals(checkUp.getIdProfissionalSaude(), resultado.getIdProfissionalSaude());
        verify(checkUpGateway).save(any());
    }
}

