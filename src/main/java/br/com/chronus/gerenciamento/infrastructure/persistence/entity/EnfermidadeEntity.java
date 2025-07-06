package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enfermidade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnfermidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enfermidade")
    private Integer idEnfermidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "enfermidade", length = 50, nullable = false)
    private EnumEnfermidade enfermidade;

    @Column(name = "descricao_enfermidade", length = 100)
    private String descricaoEnfermidade;

    @Column(name = "cid", length = 20)
    private String cid;
}