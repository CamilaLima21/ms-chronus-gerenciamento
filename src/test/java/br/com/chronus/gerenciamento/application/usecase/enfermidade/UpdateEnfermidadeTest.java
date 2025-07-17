package br.com.chronus.gerenciamento.application.usecase.enfermidade;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.dto.enfermidades.EnfermidadeRequest;
import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import br.com.chronus.gerenciamento.application.gateway.EnfermidadeGateway;
import br.com.chronus.gerenciamento.application.usecase.enfermidade.exception.EnfermidadeNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateEnfermidadeTest {

    private EnfermidadeGateway gateway;
    private UpdateEnfermidade updateEnfermidade;

    @BeforeEach
    void setUp() {
        gateway = mock(EnfermidadeGateway.class);
        updateEnfermidade = new UpdateEnfermidade(gateway);
    }

    @Test
    void deveAtualizarEnfermidadeComSucesso() {

        int id = 1;
        Enfermidade enfermidadeExistente = Enfermidade.builder()
                .idEnfermidade(id)
                .enfermidade(EnumEnfermidade.HIPERTENSAO_ARTERIAL)
                .descricaoEnfermidade("Pressão alta")
                .cid("I10")
                .build();

        EnfermidadeRequest request = new EnfermidadeRequest();
        request.setEnfermidade(EnumEnfermidade.DIABETES);
        request.setDescricaoEnfermidade("Diabetes tipo 2");
        request.setCid("E11");

        Enfermidade enfermidadeAtualizada = Enfermidade.builder()
                .idEnfermidade(id)
                .enfermidade(request.getEnfermidade())
                .descricaoEnfermidade(request.getDescricaoEnfermidade())
                .cid(request.getCid())
                .build();

        when(gateway.findEnfermidadeById(id)).thenReturn(Optional.of(enfermidadeExistente));
        when(gateway.update(any(Enfermidade.class))).thenReturn(enfermidadeAtualizada);

        Enfermidade resultado = updateEnfermidade.execute(id, request);

        assertNotNull(resultado);
        assertEquals(request.getEnfermidade(), resultado.getEnfermidade());
        assertEquals(request.getDescricaoEnfermidade(), resultado.getDescricaoEnfermidade());
        assertEquals(request.getCid(), resultado.getCid());
        verify(gateway).findEnfermidadeById(id);
        verify(gateway).update(enfermidadeExistente);
    }

    @Test
    void deveLancarExcecaoQuandoEnfermidadeNaoEncontrada() {

        int id = 2;
        EnfermidadeRequest request = new EnfermidadeRequest();
        request.setEnfermidade(EnumEnfermidade.ASMA);
        request.setDescricaoEnfermidade("Asma brônquica");
        request.setCid("J45");

        when(gateway.findEnfermidadeById(id)).thenReturn(Optional.empty());

        assertThrows(EnfermidadeNaoEncontradaException.class,
                () -> updateEnfermidade.execute(id, request));

        verify(gateway).findEnfermidadeById(id);
        verify(gateway, never()).update(any());
    }
}
