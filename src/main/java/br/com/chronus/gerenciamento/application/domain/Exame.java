package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exame {

    private Integer idExame;
    private Integer idPaciente;
    private Integer idProfissionalSaude;
    private LocalDateTime dataExame;
    private List<EnumExame> listaExames;
    private EnumStatusExame statusExame;

    public static Exame createExame(
            final Integer idPaciente,
            final Integer idProfissionalSaude,
            final LocalDateTime dataExame,
            final List<EnumExame> listaExames,
            final EnumStatusExame statusExame) {
        return new Exame(null, idPaciente, idProfissionalSaude, dataExame, listaExames, statusExame);
    }
}
