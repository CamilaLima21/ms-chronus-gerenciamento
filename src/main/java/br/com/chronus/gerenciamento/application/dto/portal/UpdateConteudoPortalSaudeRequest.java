package br.com.chronus.gerenciamento.application.dto.portal;

import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateConteudoPortalSaudeRequest {

    @NotNull(message = "O filtro do portal de saúde é obrigatório")
    private EnumFiltroPortalSaude filtroPortalSaude;

    @Size(max = 10, message = "É permitido no máximo 10 conteúdos associados")
    private List<UpdateConteudoPortalSaudeRequest> conteudos;
}
