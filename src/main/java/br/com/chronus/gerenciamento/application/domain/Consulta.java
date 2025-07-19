package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consulta {

    private Integer idConsulta;
    private Integer idPaciente;
    private Integer idProfissionalSaude;
    private LocalDateTime dataHoraConsulta;
    private String observacaoConsulta;
    private EnumStatusConsulta statusConsulta;
    private EnumTipoConsulta tipoConsulta;
    private String motivoCancelamento;

    public static Consulta createConsulta(
            final Integer idPaciente,
            final Integer idProfissionalSaude,
            final LocalDateTime dataHoraConsulta,
            final String observacaoConsulta,
            final EnumStatusConsulta statusConsulta,
            final EnumTipoConsulta tipoConsulta,
            final String motivoCancelamento) {
        return new Consulta(null, idPaciente, idProfissionalSaude, dataHoraConsulta, observacaoConsulta, statusConsulta, tipoConsulta, motivoCancelamento);
    }
}
