package br.com.chronus.gerenciamento.application.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HistoricoTest {

    @Test
    void deveConstruirHistoricoComBuilder() {
        Enfermidade enfermidade = new Enfermidade();
        Tratamento tratamento = new Tratamento();
        Consulta consulta = new Consulta();
        CheckUpSaude checkup = new CheckUpSaude();

        Historico historico = Historico.builder()
                .id(1)
                .idPaciente(10)
                .enfermidades(List.of(enfermidade))
                .tratamentos(List.of(tratamento))
                .consultas(List.of(consulta))
                .checkups(List.of(checkup))
                .observacoes("Paciente com histórico leve")
                .dataInicio(LocalDate.of(2023, 1, 1))
                .dataFim(LocalDate.of(2023, 12, 31))
                .build();

        assertThat(historico.getId()).isEqualTo(1);
        assertThat(historico.getIdPaciente()).isEqualTo(10);
        assertThat(historico.getEnfermidades()).containsExactly(enfermidade);
        assertThat(historico.getTratamentos()).containsExactly(tratamento);
        assertThat(historico.getConsultas()).containsExactly(consulta);
        assertThat(historico.getCheckups()).containsExactly(checkup);
        assertThat(historico.getObservacoes()).isEqualTo("Paciente com histórico leve");
        assertThat(historico.getDataInicio()).isEqualTo(LocalDate.of(2023, 1, 1));
        assertThat(historico.getDataFim()).isEqualTo(LocalDate.of(2023, 12, 31));
    }

    @Test
    void devePermitirCriarHistoricoComConstrutorCompleto() {
        Historico historico = new Historico(
                2,
                20,
                null,
                null,
                null,
                null,
                "Observação qualquer",
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 6, 30)
        );

        assertThat(historico.getId()).isEqualTo(2);
        assertThat(historico.getIdPaciente()).isEqualTo(20);
        assertThat(historico.getObservacoes()).isEqualTo("Observação qualquer");
        assertThat(historico.getDataInicio()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(historico.getDataFim()).isEqualTo(LocalDate.of(2024, 6, 30));
    }

    @Test
    void devePermitirAlterarCamposComSetters() {
        Historico historico = new Historico();
        historico.setId(5);
        historico.setIdPaciente(100);
        historico.setObservacoes("Atualizado");
        historico.setDataInicio(LocalDate.of(2022, 5, 1));
        historico.setDataFim(LocalDate.of(2022, 12, 31));

        assertThat(historico.getId()).isEqualTo(5);
        assertThat(historico.getIdPaciente()).isEqualTo(100);
        assertThat(historico.getObservacoes()).isEqualTo("Atualizado");
        assertThat(historico.getDataInicio()).isEqualTo(LocalDate.of(2022, 5, 1));
        assertThat(historico.getDataFim()).isEqualTo(LocalDate.of(2022, 12, 31));
    }
}
