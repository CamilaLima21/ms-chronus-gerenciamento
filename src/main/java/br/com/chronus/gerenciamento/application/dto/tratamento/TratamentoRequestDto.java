package br.com.chronus.gerenciamento.application.dto.tratamento;

import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TratamentoRequestDto {

    @NotNull(message = "O ID do paciente é obrigatório")
    private Integer idPaciente;

    @NotNull(message = "Os IDs dos medicamentos são obrigatórios")
    private List<Integer> idMedicamentos;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDate inicioTratamento;

    @NotNull(message = "A data de fim é obrigatória")
    private LocalDate fimTratamento;

    @NotBlank(message = "A periodicidade é obrigatória")
    private String periodicidade;

    @Size(max = 255, message = "A dosagem deve ter no máximo 255 caracteres")
    private String dosagem;

    @NotNull(message = "Os horários são obrigatórios")
    private List<HorarioEnum> horarios;
}
