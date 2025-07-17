package br.com.chronus.gerenciamento.application.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Historico {

    private Integer id;
    private Integer idPaciente;
    private Integer enfermidades;
    private Integer medicamentos;
    private Integer tratamento;
    private Integer consulta;
    private Integer idCheckup;
    private String observacoes;
    private LocalDate dataInicio;
    private LocalDate dataFim;

}
