package br.com.chronus.gerenciamento.application.usecase.conteudo.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;

import static java.lang.String.format;

public class ConteudoNaoEncontradoException extends BusinessException {

    private static final String ERROR_CODE = "not_found";
    private static final String MESSAGE = "Conteúdo com id [%s] não encontrado.";

    public ConteudoNaoEncontradoException(final Integer id) {
        super(format(MESSAGE, id), ERROR_CODE);
    }
}
