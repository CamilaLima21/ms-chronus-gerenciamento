package br.com.chronus.gerenciamento.application.usecase.exame.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;
import lombok.Getter;

import static java.lang.String.format;

@Getter
public class ExameExistenteException extends BusinessException {

    private static final String ERROR_CODE = "already_exists";
    private static final String MESSAGE = "Exame com id [%s] já existe.";

    public ExameExistenteException(final Integer idExame) {
        super(format(MESSAGE, idExame), ERROR_CODE);
    }
}