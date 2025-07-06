package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
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

    @Column(name = "enfermidade_id", nullable = false)
    private Integer enfermidades;

    @Column(name = "medicacao_id", nullable = false)
    private Integer medicamentos;

    @Column(name = "tratamento_id")
    private Integer tratamento;

    @Column(name = "consulta_id")
    private Integer consulta;

    @Column(name = "id_checkup")
    private Integer idCheckup;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

}