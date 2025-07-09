package br.com.chronus.gerenciamento.application.usecase.consulta.exception;

public class PacienteNaoEncontradoException extends RuntimeException {
    public PacienteNaoEncontradoException(Integer idPaciente) {
        super("Paciente com ID " + idPaciente + " não encontrado.");
    }
}
