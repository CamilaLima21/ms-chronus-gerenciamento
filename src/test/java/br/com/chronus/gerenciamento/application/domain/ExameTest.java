package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExameTest {

    @Test
    void deveCriarExameComDadosCorretos() {

        Integer idPaciente = 10;
        Integer idProfissionalSaude = 20;
        LocalDateTime dataExame = LocalDateTime.of(2025, 7, 17, 14, 30);
        List<EnumExame> listaExames = Arrays.asList(
                EnumExame.ESPIROMETRIA_ASMA,
                EnumExame.GLICEMIA_JEJUM_DIABETES
        );
        EnumStatusExame statusExame = EnumStatusExame.AGENDADO;

        Exame exame = Exame.createExame(
                idPaciente,
                idProfissionalSaude,
                dataExame,
                listaExames,
                statusExame
        );

        assertNull(exame.getIdExame(), "O idExame deve ser null ao criar");
        assertEquals(idPaciente, exame.getIdPaciente());
        assertEquals(idProfissionalSaude, exame.getIdProfissionalSaude());
        assertEquals(dataExame, exame.getDataExame());
        assertNotNull(exame.getListaExames());
        assertEquals(2, exame.getListaExames().size());
        assertTrue(exame.getListaExames().contains(EnumExame.ESPIROMETRIA_ASMA));
        assertTrue(exame.getListaExames().contains(EnumExame.GLICEMIA_JEJUM_DIABETES));
        assertEquals(statusExame, exame.getStatusExame());
    }

    @Test
    void deveRetornarDescricaoCorretaParaStatusExame() {

        assertEquals("Exame agendado", EnumStatusExame.AGENDADO.getDescricao());
        assertEquals("Exame em andamento", EnumStatusExame.EM_ANDAMENTO.getDescricao());
        assertEquals("Exame concluído", EnumStatusExame.CONCLUIDO.getDescricao());
        assertEquals("Exame cancelado", EnumStatusExame.CANCELADO.getDescricao());
        assertEquals("Exame não realizado", EnumStatusExame.NAO_REALIZADO.getDescricao());
        assertEquals("Aguardando resultado", EnumStatusExame.AGUARDANDO_RESULTADO.getDescricao());
        assertEquals("Resultado disponível", EnumStatusExame.RESULTADO_DISPONIVEL.getDescricao());
        assertEquals("Exame reagendado", EnumStatusExame.REAGENDADO.getDescricao());
    }
}
