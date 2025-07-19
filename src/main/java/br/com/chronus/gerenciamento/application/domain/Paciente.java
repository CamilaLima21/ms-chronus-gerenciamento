package br.com.chronus.gerenciamento.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paciente {

    private Integer idPaciente;
    private String nomePaciente;
    private String emailPaciente;
    private String cpfPaciente;
    private String telefonePaciente;
    private LocalDate dtNascPaciente;
    private String enderecoPaciente;
    private List<ContatoAnjo> contatoAnjoList;

}
