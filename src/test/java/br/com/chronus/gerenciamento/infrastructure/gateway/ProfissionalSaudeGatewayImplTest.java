package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.infrastructure.integration.PessoasClient;
import feign.FeignException;
import feign.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.charset.Charset;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ProfissionalSaudeGatewayImplTest {

    @Mock
    private PessoasClient pessoasClient;

    @InjectMocks
    private ProfissionalSaudeGatewayImpl profissionalSaudeGateway;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVerificaProfissionalPorIdRetornaTrueQuandoExiste() {
        // Quando não lançar exceção, significa que o profissional existe
        doNothing().when(pessoasClient).getProfissionalById(1);

        boolean resultado = profissionalSaudeGateway.verificaProfissionalPorId(1);

        assertTrue(resultado);
        verify(pessoasClient).getProfissionalById(1);
    }

    @Test
    void testVerificaProfissionalPorIdRetornaFalseQuandoNaoExiste() {
        FeignException.NotFound notFoundException = new FeignException.NotFound(
                "Not Found",
                Request.create(Request.HttpMethod.GET, "/profissionais/999", Collections.emptyMap(), null, Charset.defaultCharset(), null),
                null,
                null);

        doThrow(notFoundException).when(pessoasClient).getProfissionalById(999);

        boolean resultado = profissionalSaudeGateway.verificaProfissionalPorId(999);

        assertFalse(resultado);
        verify(pessoasClient).getProfissionalById(999);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
}
