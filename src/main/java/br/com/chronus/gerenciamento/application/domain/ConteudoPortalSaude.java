package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConteudoPortalSaude {

    private Integer id;
    private EnumFiltroPortalSaude filtroPortalSaude;
    private List<ConteudoPortalSaude> conteudos;

    public static ConteudoPortalSaude createConteudoPortalSaude(
        final Integer id,
        final EnumFiltroPortalSaude filtroPortalSaude,
        final List<ConteudoPortalSaude> conteudos) {
        return new ConteudoPortalSaude(null, filtroPortalSaude, conteudos);
    }
}
