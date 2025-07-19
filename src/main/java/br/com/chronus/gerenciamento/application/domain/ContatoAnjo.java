package br.com.chronus.gerenciamento.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContatoAnjo {

    private Integer idContatoAnjo;
    private String nomeContatoAnjo;
    private String emailContatoAnjo;
    private String cpfContatoAnjo;
    private String telefoneContatoAnjo;
    private String parentescoContatoAnjo;
    private String observacaoContatoAnjo;
    private List<Paciente> pacienteList;
}
