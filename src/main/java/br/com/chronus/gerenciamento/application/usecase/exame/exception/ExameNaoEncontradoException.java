package br.com.chronus.gerenciamento.application.usecase.exame.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;

import static java.lang.String.format;

public class ExameNaoEncontradoException extends BusinessException {

    private static final String ERROR_CODE = "not_found";
    private static final String MESSAGE = "Exame com id [%s] não encontrado.";

    public ExameNaoEncontradoException(final Integer id) {
        super(format(MESSAGE, id), ERROR_CODE);
    }
}