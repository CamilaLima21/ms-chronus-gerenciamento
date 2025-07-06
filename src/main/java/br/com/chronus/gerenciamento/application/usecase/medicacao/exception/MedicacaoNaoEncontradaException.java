package br.com.chronus.gerenciamento.application.usecase.medicacao.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;

import static java.lang.String.format;

public class MedicacaoNaoEncontradaException extends BusinessException {

    private static final String ERROR_CODE = "not_found";
    private static final String MESSAGE = "Medicação with id [%s] not found.";

    public MedicacaoNaoEncontradaException(final Integer id) {
        super(format(MESSAGE, id), ERROR_CODE);
    }
}
