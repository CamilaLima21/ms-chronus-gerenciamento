package br.com.chronus.gerenciamento.application.usecase.conteudo.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;

import static java.lang.String.format;

public class ConteudoExistenteException extends BusinessException {

    private static final String ERROR_CODE = "already_exists";
    private static final String MESSAGE = "Conteúdo com id [%s] já existe.";

    public ConteudoExistenteException(final Integer id) {
        super(format(MESSAGE, id), ERROR_CODE);
    }
}
