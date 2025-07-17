package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HistoricoEntityTest {

    @Test
    void deveCriarHistoricoEntityComDadosCorretos() {

        Long id = 1L;
        Integer idPaciente = 100;

        EnfermidadeEntity enfermidade = EnfermidadeEntity.builder()
                .idEnfermidade(10)
                .descricaoEnfermidade("Descrição da enfermidade")
                .build();

        TratamentoEntity tratamento = TratamentoEntity.builder()
                .idTratamento(20)
                .dosagem("2x ao dia")
                .periodicidade("Diária")
                .build();

        ConsultaEntity consulta = ConsultaEntity.builder()
                .idConsulta(30)
                .observacaoConsulta("Observação da consulta")
                .build();

        CheckUpSaudeEntity checkup = CheckUpSaudeEntity.builder()
                .idCheckUpsaude(40)
                .glicemia("Normal")
                .build();

        LocalDate dataInicio = LocalDate.of(2023, 1, 1);
        LocalDate dataFim = LocalDate.of(2023, 12, 31);
        String observacoes = "Observações gerais do histórico";

        HistoricoEntity historicoEntity = HistoricoEntity.builder()
                .id(id)
                .idPaciente(idPaciente)
                .enfermidades(List.of(enfermidade))
                .tratamentos(List.of(tratamento))
                .consultas(List.of(consulta))
                .checkups(List.of(checkup))
                .observacoes(observacoes)
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .build();

        assertThat(historicoEntity.getId()).isEqualTo(id);
        assertThat(historicoEntity.getIdPaciente()).isEqualTo(idPaciente);

        assertThat(historicoEntity.getEnfermidades())
                .hasSize(1)
                .contains(enfermidade);

        assertThat(historicoEntity.getTratamentos())
                .hasSize(1)
                .contains(tratamento);

        assertThat(historicoEntity.getConsultas())
                .hasSize(1)
                .contains(consulta);

        assertThat(historicoEntity.getCheckups())
                .hasSize(1)
                .contains(checkup);

        assertThat(historicoEntity.getObservacoes()).isEqualTo(observacoes);
        assertThat(historicoEntity.getDataInicio()).isEqualTo(dataInicio);
        assertThat(historicoEntity.getDataFim()).isEqualTo(dataFim);
    }
}
