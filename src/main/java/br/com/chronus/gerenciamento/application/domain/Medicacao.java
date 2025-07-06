package br.com.chronus.gerenciamento.application.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medicacao {

    private Integer idMedicacao;
    private String nomeMedicacao;
    private String descricaoMedicacao;
    private String sigtapMedicacao;

    public static Medicacao createMedicacao(
        final String nomeMedicacao,
        final String descricaoMedicacao,
        final String sigtapMedicacao) {
        return new Medicacao(null, nomeMedicacao, descricaoMedicacao, sigtapMedicacao);
    }
}
