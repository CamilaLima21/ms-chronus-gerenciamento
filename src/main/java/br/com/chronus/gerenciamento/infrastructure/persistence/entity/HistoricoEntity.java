package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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