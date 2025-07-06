package br.com.chronus.gerenciamento.application.dto;

import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UpdateExameRequest {

    @NotNull(message = "O paciente é obrigatório")
    private Integer idPaciente;

    @NotNull(message = "O profissional de saúde é obrigatório")
    private Integer idProfissionalSaude;

    @NotNull(message = "A data do exame é obrigatória")
    private LocalDateTime dataExame;

    @NotNull(message = "A lista de exames é obrigatória")
    @Size(min = 1, message = "Deve conter pelo menos um exame")
    private List<EnumExame> listaExames;

    @NotNull(message = "O status do exame é obrigatório")
    private EnumStatusExame statusExame;
}