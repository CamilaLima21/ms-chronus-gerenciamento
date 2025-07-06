package br.com.chronus.gerenciamento.infrastructure.persistence.entity;
import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exame")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exame")
    private Integer idExame;

    @Column(name = "id_paciente", nullable = false)
    private Integer idPaciente;

    @Column(name = "id_profissional_saude", nullable = false)
    private Integer idProfissionalSaude;

    @Column(name = "data_exame", nullable = false)
    private LocalDateTime dataExame;

    @ElementCollection(targetClass = EnumExame.class)
    @CollectionTable(
            name = "exame_lista_exames",
            joinColumns = @JoinColumn(name = "id_exame")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_exame")
    private List<EnumExame> listaExames;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_exame", nullable = false)
    private EnumStatusExame statusExame;
}