package br.com.chronus.gerenciamento.application.dto.mensageria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {
    private String destinatario;
    private String assunto;
    private String mensagem;
}