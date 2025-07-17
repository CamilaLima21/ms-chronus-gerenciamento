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

class PacienteGatewayImplTest {

    @Mock
    private PessoasClient pessoasClient;

    @InjectMocks
    private PacienteGatewayImpl pacienteGateway;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVerificaPacientePorIdRetornaTrueQuandoPacienteExiste() {
        // Mock para não lançar exceção (paciente existe)
        doNothing().when(pessoasClient).getPacienteById(1);

        boolean resultado = pacienteGateway.verificaPacientePorId(1);

        assertTrue(resultado);
        verify(pessoasClient).getPacienteById(1);
    }

    @Test
    void testVerificaPacientePorIdRetornaFalseQuandoPacienteNaoExiste() {
        // Mock para lançar FeignException.NotFound (paciente não existe)
        FeignException.NotFound notFoundException = new FeignException.NotFound(
                "Not Found",
                Request.create(Request.HttpMethod.GET, "/pacientes/999", Collections.emptyMap(), null, Charset.defaultCharset(), null),
                null,
                null);

        doThrow(notFoundException).when(pessoasClient).getPacienteById(999);

        boolean resultado = pacienteGateway.verificaPacientePorId(999);

        assertFalse(resultado);
        verify(pessoasClient).getPacienteById(999);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
}
