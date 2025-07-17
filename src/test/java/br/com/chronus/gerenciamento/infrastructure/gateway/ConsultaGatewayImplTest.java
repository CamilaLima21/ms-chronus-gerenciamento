package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ConsultaEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.ConsultaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultaGatewayImplTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private ConsultaGatewayImpl consultaGateway;

    private Consulta consultaDomain;
    private ConsultaEntity consultaEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        LocalDate data = LocalDate.of(2025, 7, 17);

        consultaDomain = Consulta.builder()
                .idConsulta(1)
                .idPaciente(100)
                .idProfissionalSaude(200)
                .dataHoraConsulta(data)
                .observacaoConsulta("Rotina")
                .statusConsulta(EnumStatusConsulta.CONFIRMADA)
                .tipoConsulta(EnumTipoConsulta.PRESENCIAL)
                .motivoCancelamento(null)
                .build();

        consultaEntity = ConsultaEntity.builder()
                .idConsulta(1)
                .idPaciente(100)
                .idProfissionalSaude(200)
                .dataHoraConsulta(data)
                .observacaoConsulta("Rotina")
                .statusConsulta(EnumStatusConsulta.CONFIRMADA)
                .tipoConsulta(EnumTipoConsulta.PRESENCIAL)
                .motivoCancelamento(null)
                .build();
    }

    @Test
    void deveCriarConsultaComSucesso() {
        when(consultaRepository.save(any())).thenReturn(consultaEntity);

        Consulta resultado = consultaGateway.createConsulta(consultaDomain);

        assertNotNull(resultado);
        assertEquals(consultaDomain.getIdConsulta(), resultado.getIdConsulta());
        verify(consultaRepository, times(1)).save(any());
    }

    @Test
    void deveBuscarConsultaPorIdQuandoExistente() {
        when(consultaRepository.findById(1)).thenReturn(Optional.of(consultaEntity));

        Optional<Consulta> resultado = consultaGateway.getConsultaById(1);

        assertTrue(resultado.isPresent());
        assertEquals(consultaEntity.getIdConsulta(), resultado.get().getIdConsulta());
        verify(consultaRepository, times(1)).findById(1);
    }

    @Test
    void deveRetornarVazioQuandoConsultaNaoExiste() {
        when(consultaRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Consulta> resultado = consultaGateway.getConsultaById(1);

        assertTrue(resultado.isEmpty());
        verify(consultaRepository, times(1)).findById(1);
    }

    @Test
    void deveAtualizarConsultaComSucesso() {
        when(consultaRepository.findById(1)).thenReturn(Optional.of(consultaEntity));
        when(consultaRepository.save(any())).thenReturn(consultaEntity);

        Consulta resultado = consultaGateway.updateConsulta(consultaDomain);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdConsulta());
        verify(consultaRepository, times(1)).findById(1);
        verify(consultaRepository, times(1)).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoAtualizarConsultaInexistente() {
        when(consultaRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> consultaGateway.updateConsulta(consultaDomain));

        assertEquals("Consulta não encontrada com o ID [1]", exception.getMessage());
        verify(consultaRepository, times(1)).findById(1);
        verify(consultaRepository, never()).save(any());
    }

    @Test
    void deveDeletarConsultaComSucesso() {
        when(consultaRepository.findById(1)).thenReturn(Optional.of(consultaEntity));

        consultaGateway.deleteConsulta(1);

        verify(consultaRepository, times(1)).delete(consultaEntity);
    }

    @Test
    void deveLancarExcecaoAoDeletarConsultaInexistente() {
        when(consultaRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> consultaGateway.deleteConsulta(1));

        assertEquals("Consulta não encontrada com o ID [1]", exception.getMessage());
        verify(consultaRepository, times(1)).findById(1);
        verify(consultaRepository, never()).delete(any());
    }
}
