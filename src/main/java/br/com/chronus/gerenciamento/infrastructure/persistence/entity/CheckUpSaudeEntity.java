package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkup_saude")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckUpSaudeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_checkup_saude")
    private Integer idCheckUpsaude;

    @Column(name = "id_paciente", nullable = false)
    private Integer idPaciente;

    @Column(name = "id_profissional_saude" , nullable = false)
    private Integer idProfissionalSaude;

    @Column(name = "glicemia")
    private String glicemia;

    @Column(name = "pressao_arterial")
    private String pressaoArterial;

    @Column(name = "frequencia_cardiaca")
    private String frequenciaCardiaca;

    @Column(name = "frequencia_respiratoria")
    private String frequenciaRespiratoria;

    @Column(name = "temperatura_corporal")
    private String temperaturaCorporal;

    @Column(name = "saturacao_oxigenio")
    private String saturacaoOxigenio;

    @Column(name = "outros_dados")
    private String outrosDados;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "data_hora_registro", nullable = false)
    private LocalDateTime dataHoraRegistro;
}
