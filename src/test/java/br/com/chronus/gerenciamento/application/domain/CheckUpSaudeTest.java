package br.com.chronus.gerenciamento.application.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CheckUpSaudeTest {

    @Test
    void deveCriarObjetoComBuilder() {
        LocalDateTime agora = LocalDateTime.now();

        CheckUpSaude checkUp = CheckUpSaude.builder()
                .idCheckUpsaude(1)
                .idPaciente(10)
                .idProfissionalSaude(20)
                .glicemia("90")
                .pressaoArterial("120/80")
                .frequenciaCardiaca("75")
                .frequenciaRespiratoria("18")
                .temperaturaCorporal("36.5")
                .saturacaoOxigenio("98%")
                .outrosDados("Sem alterações")
                .observacoes("Paciente estável")
                .dataHoraRegistro(agora)
                .build();

        assertEquals(1, checkUp.getIdCheckUpsaude());
        assertEquals(10, checkUp.getIdPaciente());
        assertEquals(20, checkUp.getIdProfissionalSaude());
        assertEquals("90", checkUp.getGlicemia());
        assertEquals("120/80", checkUp.getPressaoArterial());
        assertEquals("75", checkUp.getFrequenciaCardiaca());
        assertEquals("18", checkUp.getFrequenciaRespiratoria());
        assertEquals("36.5", checkUp.getTemperaturaCorporal());
        assertEquals("98%", checkUp.getSaturacaoOxigenio());
        assertEquals("Sem alterações", checkUp.getOutrosDados());
        assertEquals("Paciente estável", checkUp.getObservacoes());
        assertEquals(agora, checkUp.getDataHoraRegistro());
    }

    @Test
    void deveCriarCheckUpSaudeComMetodoStatico() {
        CheckUpSaude checkUp = CheckUpSaude.createCheckUpSaude(
                1,
                2,
                "85",
                "110/70",
                "70",
                "16",
                "36.8",
                "97%",
                "Paciente sem queixas",
                "Nenhuma observação");

        assertNull(checkUp.getIdCheckUpsaude());
        assertEquals(1, checkUp.getIdPaciente());
        assertEquals(2, checkUp.getIdProfissionalSaude());
        assertEquals("85", checkUp.getGlicemia());
        assertEquals("110/70", checkUp.getPressaoArterial());
        assertEquals("70", checkUp.getFrequenciaCardiaca());
        assertEquals("16", checkUp.getFrequenciaRespiratoria());
        assertEquals("36.8", checkUp.getTemperaturaCorporal());
        assertEquals("97%", checkUp.getSaturacaoOxigenio());
        assertEquals("Paciente sem queixas", checkUp.getOutrosDados());
        assertEquals("Nenhuma observação", checkUp.getObservacoes());
        assertNotNull(checkUp.getDataHoraRegistro());
    }

    @Test
    void deveSetarEAcessarCamposComGettersESetters() {
        CheckUpSaude checkUp = new CheckUpSaude();

        checkUp.setIdCheckUpsaude(99);
        checkUp.setIdPaciente(123);
        checkUp.setIdProfissionalSaude(456);
        checkUp.setGlicemia("100");
        checkUp.setPressaoArterial("130/85");
        checkUp.setFrequenciaCardiaca("80");
        checkUp.setFrequenciaRespiratoria("20");
        checkUp.setTemperaturaCorporal("37");
        checkUp.setSaturacaoOxigenio("96%");
        checkUp.setOutrosDados("Nada relevante");
        checkUp.setObservacoes("Sem anormalidades");
        LocalDateTime agora = LocalDateTime.now();
        checkUp.setDataHoraRegistro(agora);

        assertEquals(99, checkUp.getIdCheckUpsaude());
        assertEquals(123, checkUp.getIdPaciente());
        assertEquals(456, checkUp.getIdProfissionalSaude());
        assertEquals("100", checkUp.getGlicemia());
        assertEquals("130/85", checkUp.getPressaoArterial());
        assertEquals("80", checkUp.getFrequenciaCardiaca());
        assertEquals("20", checkUp.getFrequenciaRespiratoria());
        assertEquals("37", checkUp.getTemperaturaCorporal());
        assertEquals("96%", checkUp.getSaturacaoOxigenio());
        assertEquals("Nada relevante", checkUp.getOutrosDados());
        assertEquals("Sem anormalidades", checkUp.getObservacoes());
        assertEquals(agora, checkUp.getDataHoraRegistro());
    }
}
