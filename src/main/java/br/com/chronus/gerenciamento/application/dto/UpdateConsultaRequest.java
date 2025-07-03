package br.com.chronus.gerenciamento.application.dto;

import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateConsultaRequest {

    private Integer idProfissionalSaude;
    private LocalDate dataHoraConsulta;
    private EnumStatusConsulta statusConsulta;
    private EnumTipoConsulta tipoConsulta;
}
