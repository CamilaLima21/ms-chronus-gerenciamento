package br.com.chronus.gerenciamento.application.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckUpSaude {

    private Integer idCheckUpsaude;
    private Integer idPaciente;
    private Integer idProfissionalSaude;
    private String glicemia;
    private String pressaoArterial;
    private String frequenciaCardiaca;
    private String frequenciaRespiratoria;
    private String temperaturaCorporal;
    private String saturacaoOxigenio;
    private String outrosDados;
    private String observacoes;
    private LocalDateTime dataHoraRegistro;

    public static CheckUpSaude createCheckUpSaude(
            final Integer idPaciente,
            final Integer idProfissionalSaude,
            final String glicemia,
            final String pressaoArterial,
            final String frequenciaCardiaca,
            final String frequenciaRespiratoria,
            final String temperaturaCorporal,
            final String saturacaoOxigenio,
            final String outrosDados,
            final String observacoes) {
        return new CheckUpSaude(null, idPaciente, idProfissionalSaude, glicemia, pressaoArterial, frequenciaCardiaca, frequenciaRespiratoria, temperaturaCorporal, saturacaoOxigenio, outrosDados, observacoes, LocalDateTime.now());
    }

}
