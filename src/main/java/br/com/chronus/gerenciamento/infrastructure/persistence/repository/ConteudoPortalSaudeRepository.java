package br.com.chronus.gerenciamento.infrastructure.persistence.repository;

import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ConteudoPortalSaudeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConteudoPortalSaudeRepository extends JpaRepository<ConteudoPortalSaudeEntity, Integer> {

    List<ConteudoPortalSaudeEntity> findByFiltroPortalSaude(EnumFiltroPortalSaude filtroPortalSaude);
}
