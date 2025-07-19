package br.com.chronus.gerenciamento.application.dto.mensageria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TelefoneRequest {
    private String numero;
    private String mensagem;
}