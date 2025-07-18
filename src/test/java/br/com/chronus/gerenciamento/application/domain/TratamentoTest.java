package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TratamentoTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveCriarTratamentoComSucesso() {
        Medicacao medicacao1 = Medicacao.builder()
                .idMedicacao(1)
                .nomeMedicacao("Paracetamol")
                .descricaoMedicacao("Para febre")
                .sigtapMedicacao("123456")
                .build();

        Medicacao medicacao2 = Medicacao.builder()
                .idMedicacao(2)
                .nomeMedicacao("Amoxicilina")
                .descricaoMedicacao("Antibiótico")
                .sigtapMedicacao("7891011")
                .build();

        List<Medicacao> medicamentos = List.of(medicacao1, medicacao2);
        LocalDate inicioTratamento = LocalDate.of(2025, 7, 1);
        LocalDate fimTratamento = LocalDate.of(2025, 7, 15);
        String periodicidade = "2 vezes ao dia";
        String dosagem = "500mg";
        List<HorarioEnum> horarios = List.of(HorarioEnum.H08, HorarioEnum.H20);
        Integer idPaciente = 101;

        Tratamento tratamento = Tratamento.createTratamento(
                idPaciente,
                medicamentos,
                inicioTratamento,
                fimTratamento,
                periodicidade,
                dosagem,
                horarios
        );

        assertNotNull(tratamento);
        assertNull(tratamento.getIdTratamento());
        assertEquals(idPaciente, tratamento.getIdPaciente());
        assertEquals(2, tratamento.getMedicamentos().size());
        assertEquals("Paracetamol", tratamento.getMedicamentos().get(0).getNomeMedicacao());
        assertEquals(inicioTratamento, tratamento.getInicioTratamento());
        assertEquals(fimTratamento, tratamento.getFimTratamento());
        assertEquals("2 vezes ao dia", tratamento.getPeriodicidade());
        assertEquals("500mg", tratamento.getDosagem());
        assertEquals(2, tratamento.getHorarios().size());
        assertTrue(tratamento.getHorarios().contains(HorarioEnum.H08));
        assertTrue(tratamento.getHorarios().contains(HorarioEnum.H20));
    }

    @Test
    void deveValidarCamposObrigatorios() {
                Tratamento tratamento = new Tratamento();

        Set<ConstraintViolation<Tratamento>> violations = validator.validate(tratamento);

        assertFalse(violations.isEmpty());

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("idPaciente")
                && v.getMessage().contains("obrigatório")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("medicamentos")
                && v.getMessage().contains("obrigatórios")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("inicioTratamento")
                && v.getMessage().contains("obrigatória")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("fimTratamento")
                && v.getMessage().contains("obrigatória")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("periodicidade")
                && v.getMessage().contains("obrigatória")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("horarios")
                && v.getMessage().contains("obrigatórios")));
    }

    @Test
    void deveValidarPeriodicidadeNaoEmBranco() {
        Medicacao medicacao = Medicacao.builder()
                .idMedicacao(1)
                .nomeMedicacao("Paracetamol")
                .descricaoMedicacao("Para febre")
                .sigtapMedicacao("123456")
                .build();

        Tratamento tratamento = Tratamento.builder()
                .idPaciente(999)
                .medicamentos(List.of(medicacao))
                .inicioTratamento(LocalDate.of(2025, 7, 1))
                .fimTratamento(LocalDate.of(2025, 7, 10))
                .periodicidade("   ")  // Apenas espaços em branco
                .dosagem("500mg")
                .horarios(List.of(HorarioEnum.H08))
                .build();

        Set<ConstraintViolation<Tratamento>> violations = validator.validate(tratamento);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("periodicidade")
                && v.getMessage().contains("obrigatória")));
    }
}
