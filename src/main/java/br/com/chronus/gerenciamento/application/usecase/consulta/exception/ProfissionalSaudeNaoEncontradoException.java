package br.com.chronus.gerenciamento.application.usecase.consulta.exception;

public class ProfissionalSaudeNaoEncontradoException extends RuntimeException {
    public ProfissionalSaudeNaoEncontradoException(Integer idProfissional) {
        super("Profissional de saúde com ID " + idProfissional + " não encontrado.");
    }
}
