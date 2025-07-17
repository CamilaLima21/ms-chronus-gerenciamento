package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enfermidade {

    private Integer idEnfermidade;
    private EnumEnfermidade enfermidade;
    private String descricaoEnfermidade;
    private String cid;

    public static Enfermidade createEnfermidade(
            final EnumEnfermidade enfermidade,
            final String descricaoEnfermidade,
            final String cid) {
        return new Enfermidade(null, enfermidade, descricaoEnfermidade, cid);

    }
}
