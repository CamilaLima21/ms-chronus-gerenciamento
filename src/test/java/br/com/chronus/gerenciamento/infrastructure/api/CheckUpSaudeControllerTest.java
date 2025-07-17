package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.dto.checkup.CheckUpSaudeRequest;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.mapper.CheckUpMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CheckUpSaudeControllerTest {

    @Mock
    private CheckUpSaudeGateway checkUpSaudeGateway;

    @Mock
    private CheckUpMapper checkUpMapper;

    @InjectMocks
    private CheckUpSaudeController controller;

    private CheckUpSaudeRequest request;
    private CheckUpSaude checkUpSaude;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        request = new CheckUpSaudeRequest();
        request.setIdPaciente(1);
        request.setIdProfissionalSaude(2);
        request.setGlicemia("90");
        request.setPressaoArterial("120/80");
        request.setFrequenciaCardiaca("70");
        request.setFrequenciaRespiratoria("16");
        request.setTemperaturaCorporal("36.5");
        request.setSaturacaoOxigenio("98%");
        request.setOutrosDados("Sem observações adicionais");
        request.setObservacoes("Paciente em boas condições.");

        checkUpSaude = CheckUpSaude.builder()
                .idCheckUpsaude(10)
                .idPaciente(1)
                .idProfissionalSaude(2)
                .glicemia("90")
                .pressaoArterial("120/80")
                .frequenciaCardiaca("70")
                .frequenciaRespiratoria("16")
                .temperaturaCorporal("36.5")
                .saturacaoOxigenio("98%")
                .outrosDados("Sem observações adicionais")
                .observacoes("Paciente em boas condições.")
                .dataHoraRegistro(LocalDateTime.now())
                .build();
    }

    @Test
    void testCreate() {
        when(checkUpMapper.toDomain(request, null)).thenReturn(checkUpSaude);
        when(checkUpSaudeGateway.save(checkUpSaude)).thenReturn(checkUpSaude);

        ResponseEntity<CheckUpSaude> response = controller.create(request);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(checkUpSaude);
        verify(checkUpSaudeGateway).save(checkUpSaude);
    }

    @Test
    void testGetByIdFound() {
        when(checkUpSaudeGateway.findById(10)).thenReturn(Optional.of(checkUpSaude));

        ResponseEntity<CheckUpSaude> response = controller.getById(10);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(checkUpSaude);
    }

    @Test
    void testGetByIdNotFound() {
        when(checkUpSaudeGateway.findById(10)).thenReturn(Optional.empty());

        ResponseEntity<CheckUpSaude> response = controller.getById(10);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void testUpdate() {
        when(checkUpMapper.toDomain(request, 10)).thenReturn(checkUpSaude);
        when(checkUpSaudeGateway.update(checkUpSaude)).thenReturn(checkUpSaude);

        ResponseEntity<CheckUpSaude> response = controller.update(10, request);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(checkUpSaude);
        verify(checkUpSaudeGateway).update(checkUpSaude);
    }

    @Test
    void testDelete() {
        ResponseEntity<Void> response = controller.delete(10);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(checkUpSaudeGateway).delete(10);
    }

    @Test
    void testFindAll() {
        when(checkUpSaudeGateway.findAll()).thenReturn(List.of(checkUpSaude));

        ResponseEntity<List<CheckUpSaude>> response = controller.findAll();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).containsExactly(checkUpSaude);
    }

    @Test
    void testFindByPaciente() {
        when(checkUpSaudeGateway.findByPacienteId(1)).thenReturn(List.of(checkUpSaude));

        ResponseEntity<List<CheckUpSaude>> response = controller.findByPaciente(1);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).containsExactly(checkUpSaude);
    }

    @Test
    void testFindByProfissional() {
        when(checkUpSaudeGateway.findByProfissionalSaudeId(2)).thenReturn(List.of(checkUpSaude));

        ResponseEntity<List<CheckUpSaude>> response = controller.findByProfissional(2);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).containsExactly(checkUpSaude);
    }
}
