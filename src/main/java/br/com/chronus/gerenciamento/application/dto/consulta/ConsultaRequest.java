package br.com.chronus.gerenciamento.application.dto.consulta;

import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConsultaRequest {
    private Integer idConsulta;
    private Integer idPaciente;
    private Integer idProfissionalSaude;
    private LocalDate dataHoraConsulta;
    private String observacaoConsulta;
    private EnumStatusConsulta statusConsulta;
    private EnumTipoConsulta tipoConsulta;
    private String motivoCancelamento;
}
