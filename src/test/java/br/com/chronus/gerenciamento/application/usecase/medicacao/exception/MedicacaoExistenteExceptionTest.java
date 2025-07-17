package br.com.chronus.gerenciamento.application.usecase.medicacao.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicacaoExistenteExceptionTest {

    @Test
    void deveConstruirExcecaoComMensagemECodigoCorretos() {

        Integer idMedicacao = 1;
        String nomeMedicacao = "Dipirona";

        MedicacaoExistenteException exception = new MedicacaoExistenteException(idMedicacao, nomeMedicacao);

        assertEquals("Medicação [Dipirona] with id [1] already exists.", exception.getMessage());
        assertEquals("already_exists", exception.getErrorCode());
    }
}
