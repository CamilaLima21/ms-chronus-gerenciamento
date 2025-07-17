package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.EnumTipoMensagem;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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