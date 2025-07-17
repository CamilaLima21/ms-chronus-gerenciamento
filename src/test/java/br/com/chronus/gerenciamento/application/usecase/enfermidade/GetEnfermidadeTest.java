package br.com.chronus.gerenciamento.application.usecase.enfermidade;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import br.com.chronus.gerenciamento.application.gateway.EnfermidadeGateway;
import br.com.chronus.gerenciamento.application.usecase.enfermidade.exception.EnfermidadeNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetEnfermidadeTest {

    private EnfermidadeGateway gateway;
    private GetEnfermidade getEnfermidade;

    @BeforeEach
    void setUp() {
        gateway = mock(EnfermidadeGateway.class);
        getEnfermidade = new GetEnfermidade(gateway);
    }

    @Test
    void deveRetornarEnfermidadeQuandoEncontrada() {

        int id = 1;
        Enfermidade enfermidade = Enfermidade.builder()
                .idEnfermidade(id)
                .enfermidade(EnumEnfermidade.DIABETES)
                .descricaoEnfermidade("Controle glicêmico alterado")
                .cid("E11")
                .build();

        when(gateway.findEnfermidadeById(id)).thenReturn(Optional.of(enfermidade));

        Enfermidade resultado = getEnfermidade.execute(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIdEnfermidade());
        assertEquals(EnumEnfermidade.DIABETES, resultado.getEnfermidade());
        assertEquals("Controle glicêmico alterado", resultado.getDescricaoEnfermidade());
        assertEquals("E11", resultado.getCid());
    }

    @Test
    void deveLancarExcecaoQuandoEnfermidadeNaoEncontrada() {

        int id = 99;
        when(gateway.findEnfermidadeById(id)).thenReturn(Optional.empty());

        assertThrows(EnfermidadeNaoEncontradaException.class, () -> getEnfermidade.execute(id));
    }
}
