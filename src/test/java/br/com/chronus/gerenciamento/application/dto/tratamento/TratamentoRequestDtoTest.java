package br.com.chronus.gerenciamento.application.dto.tratamento;

import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
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

class TratamentoRequestDtoTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private TratamentoRequestDto buildValidDto() {
        TratamentoRequestDto dto = new TratamentoRequestDto();
        dto.setIdPaciente(1);
        dto.setIdMedicamentos(List.of(1, 2));
        dto.setInicioTratamento(LocalDate.now());
        dto.setFimTratamento(LocalDate.now().plusDays(7));
        dto.setPeriodicidade("Diária");
        dto.setDosagem("1 comprimido por dia");
        dto.setHorarios(List.of(HorarioEnum.H08, HorarioEnum.H20));
        return dto;
    }

    @Test
    void devePassarValidacaoComDadosValidos() {
        TratamentoRequestDto dto = buildValidDto();
        Set<ConstraintViolation<TratamentoRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Não deve haver violações para DTO válido");
    }

    @Test
    void deveFalharQuandoCamposObrigatoriosForemNulosOuVazios() {
        TratamentoRequestDto dto = new TratamentoRequestDto(); // DTO vazio
        Set<ConstraintViolation<TratamentoRequestDto>> violations = validator.validate(dto);
        assertEquals(6, violations.size(), "Deve haver 6 violações para campos obrigatórios");
    }

    @Test
    void deveFalharQuandoPeriodicidadeEstiverEmBranco() {
        TratamentoRequestDto dto = buildValidDto();
        dto.setPeriodicidade("   ");
        Set<ConstraintViolation<TratamentoRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("periodicidade")));
    }

    @Test
    void deveFalharQuandoDosagemExceder255Caracteres() {
        TratamentoRequestDto dto = buildValidDto();
        dto.setDosagem("a".repeat(256));
        Set<ConstraintViolation<TratamentoRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("dosagem")));
    }
}
