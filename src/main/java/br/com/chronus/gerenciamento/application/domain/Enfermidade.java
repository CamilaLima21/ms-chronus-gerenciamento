package br.com.chronus.gerenciamento.application.domain;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enfermidade {

    private Integer idEnfermidade;

    @Size(max = 100, message = "Name length must be less than 100 characters")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Name must contain only letters and spaces")
    private String nomeEnfermidade;
    private String cid;

    public static Enfermidade createEnfermidade(
            final String nomeEnfermidade,
            final String cid) {
        return new Enfermidade(null, nomeEnfermidade, cid);

    }
}
