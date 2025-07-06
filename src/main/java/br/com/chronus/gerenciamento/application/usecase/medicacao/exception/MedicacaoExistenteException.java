package br.com.chronus.gerenciamento.application.usecase.medicacao.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;

import static java.lang.String.format;

public class MedicacaoExistenteException extends BusinessException {

    private static final String ERROR_CODE = "already_exists";
    private static final String MESSAGE = "Medicação [%s] with id [%s] already exists.";

    public MedicacaoExistenteException(final Integer idMedicacao, final String nomeMedicacao) {
        super(format(MESSAGE, nomeMedicacao, idMedicacao), ERROR_CODE);
    }
}
