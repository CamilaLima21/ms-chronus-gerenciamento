package br.com.chronus.gerenciamento.application.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicacaoTest {

    @Test
    void deveCriarMedicacaoComMetodoCreate() {

        String nome = "Paracetamol";
        String descricao = "Analgésico e antipirético";
        String sigtap = "12345";

        Medicacao medicacao = Medicacao.createMedicacao(nome, descricao, sigtap);

        assertNull(medicacao.getIdMedicacao());
        assertEquals(nome, medicacao.getNomeMedicacao());
        assertEquals(descricao, medicacao.getDescricaoMedicacao());
        assertEquals(sigtap, medicacao.getSigtapMedicacao());
    }

    @Test
    void deveCriarMedicacaoComConstrutorEAlterarCamposComSetters() {

        Medicacao medicacao = new Medicacao();

        medicacao.setIdMedicacao(1);
        medicacao.setNomeMedicacao("Ibuprofeno");
        medicacao.setDescricaoMedicacao("Anti-inflamatório");
        medicacao.setSigtapMedicacao("67890");

        assertEquals(1, medicacao.getIdMedicacao());
        assertEquals("Ibuprofeno", medicacao.getNomeMedicacao());
        assertEquals("Anti-inflamatório", medicacao.getDescricaoMedicacao());
        assertEquals("67890", medicacao.getSigtapMedicacao());
    }
}
