package br.com.chronus.gerenciamento.application.dto.enfermidades;

import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEnfermidadeRequest {

    @NotNull(message = "O tipo de enfermidade é obrigatório")
    private EnumEnfermidade enfermidade;

    @Size(max = 100, message = "A descrição deve ter no máximo 100 caracteres")
    private String descricaoEnfermidade;

    @Size(max = 20, message = "O CID deve ter no máximo 20 caracteres")
    private String cid;
}