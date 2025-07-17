package br.com.chronus.gerenciamento.application.usecase.enfermidade;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import br.com.chronus.gerenciamento.application.gateway.EnfermidadeGateway;
import br.com.chronus.gerenciamento.application.usecase.enfermidade.exception.EnfermidadeNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteEnfermidadeTest {

    private EnfermidadeGateway gateway;
    private DeleteEnfermidade deleteEnfermidade;

    @BeforeEach
    void setUp() {
        gateway = mock(EnfermidadeGateway.class);
        deleteEnfermidade = new DeleteEnfermidade(gateway);
    }

    @Test
    void deveExcluirEnfermidadeQuandoExistente() {

        int id = 1;
        Enfermidade enfermidade = Enfermidade.builder()
                .idEnfermidade(id)
                .enfermidade(EnumEnfermidade.ASMA)
                .descricaoEnfermidade("Crise respiratória frequente")
                .cid("J45")
                .build();

        when(gateway.findEnfermidadeById(id)).thenReturn(Optional.of(enfermidade));

        deleteEnfermidade.execute(id);

        verify(gateway).delete(id);
    }

    @Test
    void deveLancarExcecaoQuandoEnfermidadeNaoEncontrada() {

        int id = 2;
        when(gateway.findEnfermidadeById(id)).thenReturn(Optional.empty());

        assertThrows(EnfermidadeNaoEncontradaException.class, () -> deleteEnfermidade.execute(id));
        verify(gateway, never()).delete(anyInt());
    }
}
