package br.com.chronus.gerenciamento.application.dto.historico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class HistoricoRequestDto {

    @NotNull(message = "O ID do paciente é obrigatório")
    private Integer idPaciente;

    @NotNull(message = "Os IDs das enfermidades são obrigatórios")
    private List<Integer> idEnfermidades;

    @NotNull(message = "Os IDs dos tratamentos são obrigatórios")
    private List<Integer> idTratamentos;

    @NotNull(message = "Os IDs das consultas são obrigatórios")
    private List<Integer> idConsultas;

    @NotNull(message = "Os IDs dos checkups são obrigatórios")
    private List<Integer> idCheckups;

    @Size(max = 500, message = "As observações devem ter no máximo 500 caracteres")
    private String observacoes;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDate dataInicio;

    @NotNull(message = "A data de fim é obrigatória")
    private LocalDate dataFim;
}