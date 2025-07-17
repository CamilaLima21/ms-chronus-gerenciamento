package br.com.chronus.gerenciamento.application.usecase.checkup;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FindAllCheckUpSaudeTest {

    private CheckUpSaudeGateway gateway;
    private FindAllCheckUpSaude findAllCheckUpSaude;

    @BeforeEach
    void setUp() {
        gateway = mock(CheckUpSaudeGateway.class);
        findAllCheckUpSaude = new FindAllCheckUpSaude(gateway);
    }

    @Test
    void deveRetornarListaDeCheckUps() {

        var checkUp1 = new CheckUpSaude();
        var checkUp2 = new CheckUpSaude();
        List<CheckUpSaude> mockList = List.of(checkUp1, checkUp2);

        when(gateway.findAll()).thenReturn(mockList);

        var result = findAllCheckUpSaude.execute();

        assertEquals(2, result.size());
        assertEquals(mockList, result);
        verify(gateway, times(1)).findAll();
    }
}
