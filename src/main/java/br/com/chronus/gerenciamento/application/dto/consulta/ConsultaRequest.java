package br.com.chronus.gerenciamento.application.dto.consulta;

import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConsultaRequest {

    /*@NotNull(message = "O ID da consulta é obrigatório")*/
    private Integer idConsulta;

    @NotNull(message = "O ID do paciente é obrigatório")
    private Integer idPaciente;

    @NotNull(message = "O ID do profissional de saúde é obrigatório")
    private Integer idProfissionalSaude;

    @NotNull(message = "A data do exame é obrigatória")
    private LocalDate dataHoraConsulta;

    private String observacaoConsulta;

    @NotNull(message = "O status da consulta é obrigatório")
    private EnumStatusConsulta statusConsulta;

    @NotNull(message = "O tipo de consulta é obrigatório")
    private EnumTipoConsulta tipoConsulta;

    private String motivoCancelamento;
}
