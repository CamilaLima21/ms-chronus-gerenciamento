package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tratamento {

    private Integer idTratamento;

    @NotNull(message = "Os medicamentos são obrigatórios")
    private List<Medicacao> medicamentos;

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
            final List<Medicacao> medicamentos,
            final LocalDate inicioTratamento,
            final LocalDate fimTratamento,
            final String periodicidade,
            final String dosagem,
            final List<HorarioEnum> horarios) {
        return new Tratamento(null, medicamentos, inicioTratamento, fimTratamento, periodicidade, dosagem, horarios);
    }
}