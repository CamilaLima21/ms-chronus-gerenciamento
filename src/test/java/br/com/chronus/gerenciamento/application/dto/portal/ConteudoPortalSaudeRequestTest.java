package br.com.chronus.gerenciamento.application.dto.portal;

import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConteudoPortalSaudeRequestTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private ConteudoPortalSaudeRequest buildValidRequest() {
        ConteudoPortalSaudeRequest request = new ConteudoPortalSaudeRequest();
        request.setFiltroPortalSaude(EnumFiltroPortalSaude.CUIDADOS_ASMA);
        request.setConteudos(List.of("Conteudo1", "Conteudo2"));
        return request;
    }

    @Test
    void testRequestValido() {
        ConteudoPortalSaudeRequest request = buildValidRequest();
        Set<ConstraintViolation<ConteudoPortalSaudeRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Request válido não deve ter violações");
    }

    @Test
    void testFiltroPortalSaudeObrigatorio() {
        ConteudoPortalSaudeRequest request = buildValidRequest();
        request.setFiltroPortalSaude(null);

        Set<ConstraintViolation<ConteudoPortalSaudeRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("filtroPortalSaude")));
    }

    @Test
    void testConteudosMaximoDez() {
        ConteudoPortalSaudeRequest request = buildValidRequest();

        // Criar lista com 11 conteúdos para violar a restrição
        List<String> conteudos = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            conteudos.add("Conteudo " + i);
        }
        request.setConteudos(conteudos);

        Set<ConstraintViolation<ConteudoPortalSaudeRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("conteudos")));
    }

    @Test
    void testConteudosPodeSerNulo() {
        ConteudoPortalSaudeRequest request = buildValidRequest();
        request.setConteudos(null);

        Set<ConstraintViolation<ConteudoPortalSaudeRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Conteudos nulo é permitido");
    }
}
