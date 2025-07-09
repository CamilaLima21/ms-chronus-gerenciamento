package br.com.chronus.gerenciamento.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
