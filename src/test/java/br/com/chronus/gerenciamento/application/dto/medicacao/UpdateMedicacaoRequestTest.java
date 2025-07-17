package br.com.chronus.gerenciamento.application.dto.medicacao;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UpdateMedicacaoRequestTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private UpdateMedicacaoRequest buildValidRequest() {
        UpdateMedicacaoRequest request = new UpdateMedicacaoRequest();
        request.setNomeMedicacao("Dipirona Sódica");
        request.setDescricaoMedicacao("Analgésico e antipirético usado para aliviar a dor.");
        request.setSigtapMedicacao("123456789");
        return request;
    }

    @Test
    void testValidRequest() {
        UpdateMedicacaoRequest request = buildValidRequest();
        Set<ConstraintViolation<UpdateMedicacaoRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "A requisição válida não deve gerar violações.");
    }

    @Test
    void testNomeMedicacaoObrigatorio() {
        UpdateMedicacaoRequest request = buildValidRequest();
        request.setNomeMedicacao(null);

        Set<ConstraintViolation<UpdateMedicacaoRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("nomeMedicacao")));
    }

    @Test
    void testNomeMedicacaoMuitoLongo() {
        UpdateMedicacaoRequest request = buildValidRequest();
        request.setNomeMedicacao("A".repeat(101));

        Set<ConstraintViolation<UpdateMedicacaoRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("nomeMedicacao")));
    }

    @Test
    void testDescricaoMedicacaoMuitoLonga() {
        UpdateMedicacaoRequest request = buildValidRequest();
        request.setDescricaoMedicacao("A".repeat(201));

        Set<ConstraintViolation<UpdateMedicacaoRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("descricaoMedicacao")));
    }

    @Test
    void testSigtapMedicacaoMuitoLongo() {
        UpdateMedicacaoRequest request = buildValidRequest();
        request.setSigtapMedicacao("A".repeat(21));

        Set<ConstraintViolation<UpdateMedicacaoRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("sigtapMedicacao")));
    }
}
