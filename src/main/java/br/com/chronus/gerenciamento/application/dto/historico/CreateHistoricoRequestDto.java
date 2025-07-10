package br.com.chronus.gerenciamento.application.dto.historico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateHistoricoRequestDto {

    @NotNull(message = "O ID do paciente é obrigatório")
    private Integer idPaciente;

    @NotNull(message = "O ID da enfermidade de saúde é obrigatório")
    private Integer idEnfermidade;

    @NotNull(message = "O ID da medicação é obrigatório")
    private Integer idMedicacao;

    @NotNull(message = "O ID do tratamento é obrigatório")
    private Integer idTratamento;

    @NotNull(message = "O ID da consulta é obrigatório")
    private Integer idConsulta;

    @NotNull(message = "O ID do checkup é obrigatório")
    private Integer idCheckup;

    @Size(max = 500, message = "As observações devem ter no máximo 500 caracteres")
    private String observacoes;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDate dataInicio;

    @NotNull(message = "A data de fim é obrigatória")
    private LocalDate dataFim;
}
