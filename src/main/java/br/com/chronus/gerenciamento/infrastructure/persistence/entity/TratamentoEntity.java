package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import br.com.chronus.gerenciamento.application.enums.HorarioEnum;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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