package br.com.chronus.gerenciamento.application.domain;

<<<<<<< HEAD
import lombok.*;

=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
>>>>>>> ee25d44ecb2ed8c9df96623ed5ce0c6866207022
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Historico {

    private Integer id;
    private Integer idPaciente;
    private List<Enfermidade> enfermidades;
    private List<Tratamento> tratamentos;
    private List<Consulta> consultas;
    private List<CheckUpSaude> checkups;
    private String observacoes;
    private LocalDate dataInicio;
    private LocalDate dataFim;

}