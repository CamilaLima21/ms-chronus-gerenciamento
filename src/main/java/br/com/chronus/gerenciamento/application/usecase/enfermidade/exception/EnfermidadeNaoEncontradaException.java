package br.com.chronus.gerenciamento.application.usecase.enfermidade.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;

import static java.lang.String.format;

public class EnfermidadeNaoEncontradaException extends BusinessException {

    private static final String ERROR_CODE = "not_found";
    private static final String MESSAGE = "Enfermidade with id [%s] not found.";

    public EnfermidadeNaoEncontradaException(final Integer id) {
        super(format(MESSAGE, id), ERROR_CODE);
    }
}
