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
    private EnumStatusConsulta statusConsulta; // Ex: "Pendente", "Confirmada", "Cancelada"
    private EnumTipoConsulta tipoConsulta; // Ex: "Presencial", "Telemedicina"
    private String motivoCancelamento; // Opcional, usado se a consulta for cancelada

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
