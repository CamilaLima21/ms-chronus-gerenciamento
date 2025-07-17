package br.com.chronus.gerenciamento.application.dto.historico;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CreateHistoricoRequestDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private CreateHistoricoRequestDto buildValidDto() {
        CreateHistoricoRequestDto dto = new CreateHistoricoRequestDto();
        dto.setIdPaciente(1);
        dto.setIdEnfermidade(2);
        dto.setIdMedicacao(3);
        dto.setIdTratamento(4);
        dto.setIdConsulta(5);
        dto.setIdCheckup(6);
        dto.setObservacoes("Observações dentro do limite.");
        dto.setDataInicio(LocalDate.now().minusDays(10));
        dto.setDataFim(LocalDate.now());
        return dto;
    }

    @Test
    void testValidDtoShouldPassValidation() {
        CreateHistoricoRequestDto dto = buildValidDto();
        Set<ConstraintViolation<CreateHistoricoRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "DTO válido não deve gerar violações");
    }

    @Test
    void testNullFieldsShouldFailValidation() {
        CreateHistoricoRequestDto dto = new CreateHistoricoRequestDto(); // tudo nulo

        Set<ConstraintViolation<CreateHistoricoRequestDto>> violations = validator.validate(dto);
        assertEquals(8, violations.size(), "Todos os campos obrigatórios devem gerar violação");
    }

    @Test
    void testObservacoesExceedingMaxLengthShouldFailValidation() {
        CreateHistoricoRequestDto dto = buildValidDto();
        dto.setObservacoes("A".repeat(501)); // acima do limite

        Set<ConstraintViolation<CreateHistoricoRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("observacoes")));
    }
}
