package br.com.chronus.gerenciamento.application.dto.enfermidades;

import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EnfermidadeRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveSerValidoQuandoTodosOsCamposEstaoCorretos() {
        EnfermidadeRequest request = new EnfermidadeRequest();
        request.setEnfermidade(EnumEnfermidade.DIABETES);
        request.setDescricaoEnfermidade("Paciente com diabetes tipo 2 controlada");
        request.setCid("E11");

        Set<ConstraintViolation<EnfermidadeRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Não deve haver violações de validação");
    }

    @Test
    void deveFalharQuandoEnfermidadeForNula() {
        EnfermidadeRequest request = new EnfermidadeRequest();
        request.setDescricaoEnfermidade("Hipertensão controlada");
        request.setCid("I10");

        Set<ConstraintViolation<EnfermidadeRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());

        ConstraintViolation<EnfermidadeRequest> violation = violations.iterator().next();
        assertEquals("O tipo de enfermidade é obrigatório", violation.getMessage());
        assertEquals("enfermidade", violation.getPropertyPath().toString());
    }

    @Test
    void deveFalharQuandoDescricaoExcedeLimite() {
        EnfermidadeRequest request = new EnfermidadeRequest();
        request.setEnfermidade(EnumEnfermidade.HIPERTENSAO_ARTERIAL);
        request.setDescricaoEnfermidade("A".repeat(101));
        request.setCid("I10");

        Set<ConstraintViolation<EnfermidadeRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());

        boolean encontrouViolacao = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("descricaoEnfermidade")
                        && v.getMessage().contains("no máximo 100 caracteres"));

        assertTrue(encontrouViolacao, "Deve haver violação por descrição longa");
    }

    @Test
    void deveFalharQuandoCidExcedeLimite() {
        EnfermidadeRequest request = new EnfermidadeRequest();
        request.setEnfermidade(EnumEnfermidade.HIPERTENSAO_ARTERIAL);
        request.setDescricaoEnfermidade("Hipertensão leve");
        request.setCid("X".repeat(21));

        Set<ConstraintViolation<EnfermidadeRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());

        boolean encontrouViolacao = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("cid")
                        && v.getMessage().contains("no máximo 20 caracteres"));

        assertTrue(encontrouViolacao, "Deve haver violação por CID longo");
    }

    @Test
    void devePermitirDescricaoENula() {
        EnfermidadeRequest request = new EnfermidadeRequest();
        request.setEnfermidade(EnumEnfermidade.ASMA);
        request.setDescricaoEnfermidade(null);
        request.setCid("J45");

        Set<ConstraintViolation<EnfermidadeRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Descrição nula deve ser permitida");
    }

    @Test
    void devePermitirCidNulo() {
        EnfermidadeRequest request = new EnfermidadeRequest();
        request.setEnfermidade(EnumEnfermidade.ASMA);
        request.setDescricaoEnfermidade("Asma intermitente");
        request.setCid(null);

        Set<ConstraintViolation<EnfermidadeRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "CID nulo deve ser permitido");
    }
}
