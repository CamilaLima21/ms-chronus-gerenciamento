package br.com.chronus.gerenciamento.application.usecase.consulta.exception;

import br.com.chronus.gerenciamento.application.usecase.exception.BusinessException;

import static java.lang.String.format;

public class ConsultaNaoEncontradaException  extends BusinessException {

    private static final String ERROR_CODE = "consulta.nao.encontrada";
    private static final String ERROR_MESSAGE = "Consulta com id [%s] não encontrada.";

    public ConsultaNaoEncontradaException(final Integer idConsulta) {
        super(format(ERROR_MESSAGE, idConsulta), ERROR_CODE);
    }
}
