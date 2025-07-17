package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ConteudoPortalSaudeEntityTest {

    @Test
    void deveCriarConteudoPortalSaudeEntityComDadosCorretos() {

        Integer id = 1;
        EnumFiltroPortalSaude filtro = EnumFiltroPortalSaude.CUIDADOS_ASMA;
        List<String> conteudos = Arrays.asList(
                "Evite exposição ao pólen",
                "Pratique exercícios respiratórios diariamente"
        );

        ConteudoPortalSaudeEntity entity = ConteudoPortalSaudeEntity.builder()
                .id(id)
                .filtroPortalSaude(filtro)
                .conteudos(conteudos)
                .build();

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getFiltroPortalSaude()).isEqualTo(filtro);
        assertThat(entity.getConteudos()).containsExactlyElementsOf(conteudos);
    }
}
