package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tratamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TratamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamento", unique = true)
    private Integer idTratamento;

    @ManyToMany
    @JoinTable(
            name = "tratamento_medicamento",
            joinColumns = @JoinColumn(name = "id_tratamento"),
            inverseJoinColumns = @JoinColumn(name = "id_medicamento")
    )
    private List<MedicacaoEntity> medicamentos;

    @Column(name = "id_paciente", nullable = false)
    private Integer idPaciente;

    @Column(name = "inicio_tratamento", nullable = false)
    private LocalDate inicioTratamento;

    @Column(name = "fim_tratamento", nullable = false)
    private LocalDate fimTratamento;

    @Column(name = "periodicidade", nullable = false, length = 20)
    private String periodicidade;

    @Column(name = "dosagem", nullable = false, length = 20)
    private String dosagem;

    @ElementCollection(targetClass = HorarioEnum.class)
    @CollectionTable(name = "tratamento_horarios", joinColumns = @JoinColumn(name = "id_tratamento"))
    @Enumerated(EnumType.STRING)
    @Column(name = "horario", length = 4)
    private List<HorarioEnum> horarios;
}