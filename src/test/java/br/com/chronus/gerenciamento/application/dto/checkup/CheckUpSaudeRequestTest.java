package br.com.chronus.gerenciamento.application.dto.checkup;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CheckUpSaudeRequestTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveSerValidoComTodosOsCamposPreenchidosCorretamente() {
        CheckUpSaudeRequest request = new CheckUpSaudeRequest();
        request.setIdPaciente(1);
        request.setIdProfissionalSaude(2);
        request.setGlicemia("85 mg/dL");
        request.setPressaoArterial("120x80");
        request.setFrequenciaCardiaca("75 bpm");
        request.setFrequenciaRespiratoria("18 rpm");
        request.setTemperaturaCorporal("36.5 °C");
        request.setSaturacaoOxigenio("98%");
        request.setOutrosDados("Sem alterações");
        request.setObservacoes("Paciente em boas condições.");

        Set<ConstraintViolation<CheckUpSaudeRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "Não deve haver violações de validação");
    }

    @Test
    void deveFalharQuandoCamposObrigatoriosForemNulos() {
        CheckUpSaudeRequest request = new CheckUpSaudeRequest();

        Set<ConstraintViolation<CheckUpSaudeRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("idPaciente")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("idProfissionalSaude")));
    }

    @Test
    void deveFalharQuandoCamposUltrapassaremTamanhoMaximo() {
        CheckUpSaudeRequest request = new CheckUpSaudeRequest();
        request.setIdPaciente(1);
        request.setIdProfissionalSaude(2);
        request.setGlicemia("x".repeat(21));
        request.setPressaoArterial("x".repeat(21));
        request.setFrequenciaCardiaca("x".repeat(21));
        request.setFrequenciaRespiratoria("x".repeat(21));
        request.setTemperaturaCorporal("x".repeat(21));
        request.setSaturacaoOxigenio("x".repeat(21));
        request.setOutrosDados("x".repeat(256));
        request.setObservacoes("x".repeat(501));

        Set<ConstraintViolation<CheckUpSaudeRequest>> violations = validator.validate(request);

        assertEquals(8, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("glicemia")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("pressaoArterial")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("frequenciaCardiaca")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("frequenciaRespiratoria")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("temperaturaCorporal")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("saturacaoOxigenio")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("outrosDados")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("observacoes")));
    }
}
