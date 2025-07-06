package br.com.chronus.gerenciamento.infrastructure.persistence.entity;

import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "conteudo_portal_saude")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConteudoPortalSaudeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conteudo")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "filtro_portal_saude", nullable = false)
    private EnumFiltroPortalSaude filtroPortalSaude;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "conteudo_principal_id")
    private List<ConteudoPortalSaudeEntity> conteudos;
}
