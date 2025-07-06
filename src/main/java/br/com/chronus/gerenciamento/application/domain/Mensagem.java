package br.com.chronus.gerenciamento.application.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.chronus.gerenciamento.application.enums.EnumTipoMensagem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mensagem {

    private Long id;

    @NotNull(message = "Sem destino definido")
    private String destino;

    @NotNull(message = "Sem tipo de mensagem definido")
    private EnumTipoMensagem tipoMensagem;

    @NotNull(message = "Mensagem não pode ser vazia")
    private String conteudo;

    public static Mensagem createMensagem(
            final String destino,
            final EnumTipoMensagem tipoMensagem,
            final String conteudo) {
        return new Mensagem(null, destino, tipoMensagem, conteudo);
    }
}