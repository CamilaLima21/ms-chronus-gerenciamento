package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ConteudoPortalSaudeEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.ConteudoPortalSaudeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConteudoPortalSaudeGatewayImpl implements ConteudoPortalSaudeGateway {

    private final ConteudoPortalSaudeRepository repository;

    @Override
    public ConteudoPortalSaude save(final ConteudoPortalSaude conteudo) {
        ConteudoPortalSaudeEntity entity = mapToEntity(conteudo);
        ConteudoPortalSaudeEntity saved = repository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<ConteudoPortalSaude> findById(final Integer id) {
        return repository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public ConteudoPortalSaude update(final ConteudoPortalSaude conteudo) {
        ConteudoPortalSaudeEntity found = repository.findById(conteudo.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Conteúdo não encontrado com o ID [%s]", conteudo.getId())));
        ConteudoPortalSaudeEntity entity = mapToEntity(conteudo);
        entity.setId(found.getId());
        ConteudoPortalSaudeEntity updated = repository.save(entity);
        return mapToDomain(updated);
    }

    @Transactional
    @Override
    public void delete(final Integer id) {
        ConteudoPortalSaudeEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Conteúdo não encontrado com o ID [%s]", id)));
        repository.delete(entity);
    }

    @Override
    public List<ConteudoPortalSaude> findAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConteudoPortalSaude> findByFiltro(final EnumFiltroPortalSaude filtro) {
        return repository.findByFiltroPortalSaude(filtro)
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private ConteudoPortalSaudeEntity mapToEntity(final ConteudoPortalSaude domain) {
        return ConteudoPortalSaudeEntity.builder()
                .id(domain.getId())
                .filtroPortalSaude(domain.getFiltroPortalSaude())
                .conteudos(domain.getConteudos())
                .build();
    }

    private ConteudoPortalSaude mapToDomain(final ConteudoPortalSaudeEntity entity) {
        return ConteudoPortalSaude.builder()
                .id(entity.getId())
                .filtroPortalSaude(entity.getFiltroPortalSaude())
                .conteudos(entity.getConteudos())
                .build();
    }
}
