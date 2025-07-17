package br.com.chronus.gerenciamento.application.usecase.enfermidade;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import br.com.chronus.gerenciamento.application.gateway.EnfermidadeGateway;
import br.com.chronus.gerenciamento.application.usecase.enfermidade.exception.EnfermidadeExistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateEnfermidadeTest {

    private EnfermidadeGateway gateway;
    private CreateEnfermidade createEnfermidade;

    @BeforeEach
    void setUp() {
        gateway = Mockito.mock(EnfermidadeGateway.class);
        createEnfermidade = new CreateEnfermidade(gateway);
    }

    @Test
    void deveCriarNovaEnfermidadeComSucesso() {

        Enfermidade nova = Enfermidade.builder()
                .idEnfermidade(0)
                .enfermidade(EnumEnfermidade.DIABETES)
                .descricaoEnfermidade("Controle glicêmico")
                .cid("E11")
                .build();

        Enfermidade salva = Enfermidade.builder()
                .idEnfermidade(1)
                .enfermidade(EnumEnfermidade.DIABETES)
                .descricaoEnfermidade("Controle glicêmico")
                .cid("E11")
                .build();

        when(gateway.findEnfermidadeById(0)).thenReturn(Optional.empty());
        when(gateway.save(any(Enfermidade.class))).thenReturn(salva);

        Enfermidade result = createEnfermidade.execute(nova);

        assertNotNull(result);
        assertEquals(1, result.getIdEnfermidade());
        assertEquals(EnumEnfermidade.DIABETES, result.getEnfermidade());
        verify(gateway).save(any(Enfermidade.class));
    }


    @Test
    void deveLancarExcecaoQuandoEnfermidadeJaExistir() {

        Enfermidade existente = Enfermidade.builder()
                .idEnfermidade(5)
                .enfermidade(EnumEnfermidade.HIPERTENSAO_ARTERIAL)
                .descricaoEnfermidade("Pressão alta")
                .cid("I10")
                .build();

        when(gateway.findEnfermidadeById(5)).thenReturn(Optional.of(existente));

        Enfermidade request = Enfermidade.builder()
                .idEnfermidade(5)
                .enfermidade(EnumEnfermidade.HIPERTENSAO_ARTERIAL)
                .descricaoEnfermidade("Pressão alta")
                .cid("I10")
                .build();

        EnfermidadeExistenteException exception = assertThrows(
                EnfermidadeExistenteException.class,
                () -> createEnfermidade.execute(request)
        );

        String expectedMessage = String.format("Enfermidade [%s] with id [%s] already exists.",
                request.getEnfermidade().name(),
                request.getIdEnfermidade());

        assertEquals(expectedMessage, exception.getMessage());
        verify(gateway, never()).save(any());
    }
}
