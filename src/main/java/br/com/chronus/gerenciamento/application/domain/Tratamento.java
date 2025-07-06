package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Tratamento {

    private Integer idTratamento;

    private String idMedicamento;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDate inicioTratamento;

    @NotNull(message = "A data de fim é obrigatória")
    private LocalDate fimTratamento;

    @NotBlank(message = "A periodicidade é obrigatória")
    private String periodicidade;

    private String dosagem;

    @NotNull(message = "Os horários são obrigatórios")
    private List<HorarioEnum> horarios;

    public static Tratamento createTratamento(
            final String medicamento,
            final LocalDate inicioTratamento,
            final LocalDate fimTratamento,
            final String periodicidade,
            final List<HorarioEnum> horarios) {
        return new Tratamento(null, medicamento, inicioTratamento, fimTratamento, periodicidade, horarios);
    }
}