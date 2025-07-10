package br.com.chronus.gerenciamento.application.dto.medicacao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMedicacaoRequest {

    @NotNull(message = "O nome da medicação é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nomeMedicacao;

    @Size(max = 200, message = "A descrição deve ter no máximo 200 caracteres")
    private String descricaoMedicacao;

    @Size(max = 20, message = "O SIGTAP deve ter no máximo 20 caracteres")
    private String sigtapMedicacao;
}