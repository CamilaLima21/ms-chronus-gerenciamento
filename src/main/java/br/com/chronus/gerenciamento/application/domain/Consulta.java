package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consulta {

    private Integer idConsulta;
    private Integer idPaciente;
    private Integer idProfissionalSaude;
    private LocalDate dataHoraConsulta;
    private String observacaoConsulta;
    private EnumStatusConsulta statusConsulta;
    private EnumTipoConsulta tipoConsulta;
    private String motivoCancelamento;

    public static Consulta createConsulta(
            final Integer idPaciente,
            final Integer idProfissionalSaude,
            final LocalDate dataHoraConsulta,
            final String observacaoConsulta,
            final EnumStatusConsulta statusConsulta,
            final EnumTipoConsulta tipoConsulta,
            final String motivoCancelamento) {
        return new Consulta(null, idPaciente, idProfissionalSaude, dataHoraConsulta, observacaoConsulta, statusConsulta, tipoConsulta, motivoCancelamento);
    }
}
