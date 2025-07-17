package br.com.chronus.gerenciamento.application.dto.exame;

import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExameRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private ExameRequest buildValidRequest() {
        ExameRequest request = new ExameRequest();
        request.setIdPaciente(1);
        request.setIdProfissionalSaude(2);
        request.setDataExame(LocalDateTime.now().plusDays(1));
        request.setListaExames(List.of(
                EnumExame.GLICEMIA_JEJUM_DIABETES,
                EnumExame.MAPA_HIPERTENSAO
        ));
        request.setStatusExame(EnumStatusExame.AGENDADO);
        return request;
    }


    @Test
    void deveValidarRequestValidoSemViolacoes() {
        ExameRequest request = buildValidRequest();
        Set<ConstraintViolation<ExameRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Não deveria haver violações");
    }

    @Test
    void deveValidarCamposObrigatoriosNulos() {
        ExameRequest request = new ExameRequest();
        Set<ConstraintViolation<ExameRequest>> violations = validator.validate(request);

        assertEquals(5, violations.size());

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("idPaciente")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("idProfissionalSaude")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("dataExame")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("listaExames")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("statusExame")));
    }

    @Test
    void deveValidarListaExamesVazia() {
        ExameRequest request = buildValidRequest();
        request.setListaExames(List.of());

        Set<ConstraintViolation<ExameRequest>> violations = validator.validate(request);

        assertEquals(1, violations.size());
        ConstraintViolation<ExameRequest> violation = violations.iterator().next();
        assertEquals("listaExames", violation.getPropertyPath().toString());
        assertEquals("Deve conter pelo menos um exame", violation.getMessage());
    }
}
