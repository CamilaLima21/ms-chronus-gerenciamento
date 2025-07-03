package br.com.chronus.gerenciamento.application.usecase.consulta.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;
import lombok.Getter;

import java.time.LocalDate;

import static java.lang.String.format;

@Getter
public class ConsultaExistenteException extends BusinessException {

    private static final String ERROR_CODE = "consulta.existente";
    private static final String ERROR_MESSAGE = "Consulta [%s] já cadastrado com o ID [%s] informado.";

    public ConsultaExistenteException(final Integer idConsulta, final LocalDate dataHoraConsulta) {
        super(format(ERROR_MESSAGE, idConsulta, dataHoraConsulta), ERROR_CODE);
    }
}
