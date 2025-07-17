package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "historico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoricoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_paciente", nullable = false)
    private Integer idPaciente;

    @ManyToMany
    @JoinTable(
            name = "historico_enfermidade",
            joinColumns = @JoinColumn(name = "historico_id"),
            inverseJoinColumns = @JoinColumn(name = "enfermidade_id")
    )
    private List<EnfermidadeEntity> enfermidades;

    @ManyToMany
    @JoinTable(
            name = "historico_tratamento",
            joinColumns = @JoinColumn(name = "historico_id"),
            inverseJoinColumns = @JoinColumn(name = "tratamento_id")
    )
    private List<TratamentoEntity> tratamentos;

    @ManyToMany
    @JoinTable(
            name = "historico_consulta",
            joinColumns = @JoinColumn(name = "historico_id"),
            inverseJoinColumns = @JoinColumn(name = "consulta_id")
    )
    private List<ConsultaEntity> consultas;

    @ManyToMany
    @JoinTable(
            name = "historico_checkup",
            joinColumns = @JoinColumn(name = "historico_id"),
            inverseJoinColumns = @JoinColumn(name = "checkup_id")
    )
    private List<CheckUpSaudeEntity> checkups;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;
}