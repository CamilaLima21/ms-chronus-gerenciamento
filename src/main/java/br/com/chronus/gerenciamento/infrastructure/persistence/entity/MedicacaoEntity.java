package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicacao")
    private Integer idMedicacao;

    @Column(name = "nome_medicacao", nullable = false, length = 100)
    private String nomeMedicacao;

    @Column(name = "descricao_medicacao", length = 200)
    private String descricaoMedicacao;

    @Column(name = "sigtap_medicacao", length = 20)
    private String sigtapMedicacao;
}