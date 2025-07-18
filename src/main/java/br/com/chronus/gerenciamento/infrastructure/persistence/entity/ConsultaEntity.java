package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import br.com.chronus.gerenciamento.application.enums.EnumStatusConsulta;
import br.com.chronus.gerenciamento.application.enums.EnumTipoConsulta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "consulta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConsulta;

    @Column(name = "id_paciente", nullable = false)
    private Integer idPaciente;

    @Column(name = "id_profissional_saude", nullable = false)
    private Integer idProfissionalSaude;

    @Column(name = "data_hora_consulta", nullable = false)
    private LocalDate dataHoraConsulta;

    @Column(name = "observacao_consulta")
    private String observacaoConsulta;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_consulta", nullable = false, length = 20, columnDefinition = "varchar(20)")
    private EnumStatusConsulta statusConsulta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_consulta", nullable = false, length = 20, columnDefinition = "varchar(20)")
    private EnumTipoConsulta tipoConsulta;

    @Column(name = "motivo_cancelamento")
    private String motivoCancelamento;
}