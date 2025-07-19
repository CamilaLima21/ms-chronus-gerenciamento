package br.com.chronus.gerenciamento.application.domain;

import br.com.chronus.gerenciamento.application.enums.EnumTipoMensagem;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mensagem {
    private String tipo;
    private String destinatario;
    private String numero;
    private String assunto;
    private String conteudo;
}