package br.com.chronus.gerenciamento.application.domain;

import lombok.*;

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
    private List<Enfermidade> enfermidades;
    private List<Tratamento> tratamentos;
    private List<Consulta> consultas;
    private List<CheckUpSaude> checkups;
    private String observacoes;
    private LocalDate dataInicio;
    private LocalDate dataFim;

}