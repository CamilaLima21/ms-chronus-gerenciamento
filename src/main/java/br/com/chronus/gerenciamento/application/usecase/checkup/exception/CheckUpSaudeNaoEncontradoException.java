package br.com.chronus.gerenciamento.application.usecase.checkup.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;

import static java.lang.String.format;

public class CheckUpSaudeNaoEncontradoException extends BusinessException {

    private static final String ERROR_CODE = "not_found";
    private static final String MESSAGE = "Check-up de saúde com id [%s] não encontrado.";

    public CheckUpSaudeNaoEncontradoException(final Integer id) {
        super(format(MESSAGE, id), ERROR_CODE);
    }
}
