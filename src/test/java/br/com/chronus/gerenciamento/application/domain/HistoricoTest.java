package br.com.chronus.gerenciamento.application.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoricoTest {

    @Test
    void deveCriarHistoricoComBuilderEVerificarCampos() {

        Integer id = 1;
        Integer idPaciente = 100;
        Integer enfermidades = 2;
        Integer medicamentos = 3;
        Integer tratamento = 1;
        Integer consulta = 5;
        Integer idCheckup = 7;
        String observacoes = "Paciente apresenta melhora.";
        LocalDate dataInicio = LocalDate.of(2025, 7, 1);
        LocalDate dataFim = LocalDate.of(2025, 7, 15);

        Historico historico = Historico.builder()
                .id(id)
                .idPaciente(idPaciente)
                .enfermidades(enfermidades)
                .medicamentos(medicamentos)
                .tratamento(tratamento)
                .consulta(consulta)
                .idCheckup(idCheckup)
                .observacoes(observacoes)
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .build();

        assertEquals(id, historico.getId());
        assertEquals(idPaciente, historico.getIdPaciente());
        assertEquals(enfermidades, historico.getEnfermidades());
        assertEquals(medicamentos, historico.getMedicamentos());
        assertEquals(tratamento, historico.getTratamento());
        assertEquals(consulta, historico.getConsulta());
        assertEquals(idCheckup, historico.getIdCheckup());
        assertEquals(observacoes, historico.getObservacoes());
        assertEquals(dataInicio, historico.getDataInicio());
        assertEquals(dataFim, historico.getDataFim());
    }

    @Test
    void deveAlterarCamposComSetters() {

        Historico historico = new Historico();

        historico.setId(10);
        historico.setIdPaciente(200);
        historico.setEnfermidades(4);
        historico.setMedicamentos(6);
        historico.setTratamento(2);
        historico.setConsulta(3);
        historico.setIdCheckup(8);
        historico.setObservacoes("Nova observação");
        historico.setDataInicio(LocalDate.of(2025, 8, 1));
        historico.setDataFim(LocalDate.of(2025, 8, 20));

        assertEquals(10, historico.getId());
        assertEquals(200, historico.getIdPaciente());
        assertEquals(4, historico.getEnfermidades());
        assertEquals(6, historico.getMedicamentos());
        assertEquals(2, historico.getTratamento());
        assertEquals(3, historico.getConsulta());
        assertEquals(8, historico.getIdCheckup());
        assertEquals("Nova observação", historico.getObservacoes());
        assertEquals(LocalDate.of(2025, 8, 1), historico.getDataInicio());
        assertEquals(LocalDate.of(2025, 8, 20), historico.getDataFim());
    }
}
