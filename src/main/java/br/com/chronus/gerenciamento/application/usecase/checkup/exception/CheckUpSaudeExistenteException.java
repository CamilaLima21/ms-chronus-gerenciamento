package br.com.chronus.gerenciamento.application.usecase.checkup.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;
import lombok.Getter;

import static java.lang.String.format;

@Getter
public class CheckUpSaudeExistenteException extends BusinessException {

    private static final String ERROR_CODE = "already_exists";
    private static final String MESSAGE = "Check-up de saúde com id [%s] já existe.";

    public CheckUpSaudeExistenteException(final Integer idCheckUp) {
        super(format(MESSAGE, idCheckUp), ERROR_CODE);
    }
}
