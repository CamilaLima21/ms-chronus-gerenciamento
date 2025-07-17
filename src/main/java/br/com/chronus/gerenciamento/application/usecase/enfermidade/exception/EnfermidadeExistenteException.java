package br.com.chronus.gerenciamento.application.usecase.enfermidade.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;
import lombok.Getter;

import static java.lang.String.format;

@Getter
public class EnfermidadeExistenteException extends BusinessException {

    private static final String ERROR_CODE = "already_exists";
    private static final String MESSAGE = "Enfermidade [%s] with id [%s] already exists.";

    public EnfermidadeExistenteException(final Integer idEnfermidade, final String nomeEnfermidade) {
        super(format(MESSAGE, nomeEnfermidade, idEnfermidade), ERROR_CODE);
    }
}
