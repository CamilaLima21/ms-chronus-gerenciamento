package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ConteudoPortalSaudeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ConteudoPortalSaudeRepositoryTest {

    @Autowired
    private ConteudoPortalSaudeRepository repository;

    @Test
    void deveSalvarEConsultarPorFiltroPortalSaude() {

        ConteudoPortalSaudeEntity conteudo1 = ConteudoPortalSaudeEntity.builder()
                .filtroPortalSaude(EnumFiltroPortalSaude.CUIDADOS_ASMA)
                .conteudos(Arrays.asList("Dica 1", "Dica 2"))
                .build();

        ConteudoPortalSaudeEntity conteudo2 = ConteudoPortalSaudeEntity.builder()
                .filtroPortalSaude(EnumFiltroPortalSaude.MONITORAMENTO_GLICEMICO_DIABETES)
                .conteudos(List.of("Dica diabetes"))
                .build();

        repository.save(conteudo1);
        repository.save(conteudo2);

        List<ConteudoPortalSaudeEntity> resultados = repository.findByFiltroPortalSaude(EnumFiltroPortalSaude.CUIDADOS_ASMA);

        assertThat(resultados)
                .isNotEmpty()
                .hasSize(1);

        ConteudoPortalSaudeEntity encontrado = resultados.get(0);
        assertThat(encontrado.getFiltroPortalSaude()).isEqualTo(EnumFiltroPortalSaude.CUIDADOS_ASMA);
        assertThat(encontrado.getConteudos()).containsExactly("Dica 1", "Dica 2");
    }
}
