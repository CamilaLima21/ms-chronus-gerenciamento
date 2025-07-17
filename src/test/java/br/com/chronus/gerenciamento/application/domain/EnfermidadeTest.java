package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EnfermidadeTest {

    @Test
    void deveCriarEnfermidadeComDadosCorretos() {

        EnumEnfermidade tipoEnfermidade = EnumEnfermidade.DIABETES;
        String descricao = "Doença crônica caracterizada por altos níveis de glicose no sangue.";
        String cid = "E10-E14";

        Enfermidade enfermidade = Enfermidade.createEnfermidade(tipoEnfermidade, descricao, cid);

        assertNull(enfermidade.getIdEnfermidade());
        assertEquals(tipoEnfermidade, enfermidade.getEnfermidade());
        assertEquals(descricao, enfermidade.getDescricaoEnfermidade());
        assertEquals(cid, enfermidade.getCid());
    }
}
