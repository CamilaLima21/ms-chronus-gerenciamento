package br.com.chronus.gerenciamento.application.gateway;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;

import java.util.List;
import java.util.Optional;

public interface ConteudoPortalSaudeGateway {

    ConteudoPortalSaude save(final ConteudoPortalSaude conteudo);

    Optional<ConteudoPortalSaude> findById(final Integer id);

    ConteudoPortalSaude update(final ConteudoPortalSaude conteudo);

    void delete(final Integer id);

    List<ConteudoPortalSaude> findAll();

    List<ConteudoPortalSaude> findByFiltro(final EnumFiltroPortalSaude filtro);
}
