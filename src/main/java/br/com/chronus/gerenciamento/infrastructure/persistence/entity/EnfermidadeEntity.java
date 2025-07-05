package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Size(max = 100, message = "Name length must be less than 100 characters")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Name must contain only letters and spaces")
    @Column(name = "nome_enfermidade", length = 100, nullable = false)
    private String nomeEnfermidade;

    @Column(name = "cid", length = 20)
    private String cid;
}