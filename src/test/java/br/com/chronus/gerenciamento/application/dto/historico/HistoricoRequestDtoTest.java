package br.com.chronus.gerenciamento.application.dto.historico;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HistoricoRequestDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private HistoricoRequestDto createValidHistoricoRequest() {
        HistoricoRequestDto dto = new HistoricoRequestDto();
        dto.setIdPaciente(1);
        dto.setIdEnfermidades(List.of(1, 2));
        dto.setIdTratamentos(List.of(1, 2));
        dto.setIdConsultas(List.of(1, 2));
        dto.setIdCheckups(List.of(1, 2));
        dto.setObservacoes("Paciente com histórico recente de pressão alta.");
        dto.setDataInicio(LocalDate.of(2023, 1, 1));
        dto.setDataFim(LocalDate.of(2023, 12, 31));
        return dto;
    }

    @Test
    void deveSerValidoQuandoTodosOsCamposForemPreenchidosCorretamente() {
        HistoricoRequestDto dto = createValidHistoricoRequest();

        Set<ConstraintViolation<HistoricoRequestDto>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty(), "Esperava-se que não houvesse violações de validação.");
    }

    @Test
    void deveRetornarErroQuandoCamposObrigatoriosForemNulos() {
        HistoricoRequestDto dto = new HistoricoRequestDto(); // todos os campos são nulos

        Set<ConstraintViolation<HistoricoRequestDto>> violations = validator.validate(dto);

        assertEquals(7, violations.size()); // todos os @NotNull esperados
    }

    @Test
    void deveRetornarErroQuandoObservacaoExcederLimiteDeCaracteres() {
        HistoricoRequestDto dto = createValidHistoricoRequest();
        dto.setObservacoes("A".repeat(501)); // excede 500 caracteres

        Set<ConstraintViolation<HistoricoRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("observacoes")));
    }
}
