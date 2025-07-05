package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.gateway.EnfermidadeGateway;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.EnfermidadeEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.EnfermidadeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EnfermidadeGatewayImpl implements EnfermidadeGateway {

    private final EnfermidadeRepository enfermidadeRepository;

    @Override
    public Enfermidade save(final Enfermidade enfermidade) {
        EnfermidadeEntity entity = mapToEntity(enfermidade);
        EnfermidadeEntity saved = enfermidadeRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Enfermidade> findEnfermidadeById(final int idEnfermidade) {
        return enfermidadeRepository.findById(idEnfermidade)
                .map(this::mapToDomain);
    }

    @Override
    public Enfermidade update(final Enfermidade enfermidade) {
        EnfermidadeEntity found = enfermidadeRepository.findById(enfermidade.getIdEnfermidade())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Enfermidade não encontrada com o ID [%s]", enfermidade.getIdEnfermidade())));
        EnfermidadeEntity entity = mapToEntity(enfermidade);
        entity.setIdEnfermidade(found.getIdEnfermidade());
        EnfermidadeEntity updated = enfermidadeRepository.save(entity);
        return mapToDomain(updated);
    }

    @Transactional
    @Override
    public void delete(final Integer idEnfermidade) {
        EnfermidadeEntity entity = enfermidadeRepository.findById(idEnfermidade)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Enfermidade não encontrada com o ID [%s]", idEnfermidade)));
        enfermidadeRepository.delete(entity);
    }

    @Override
    public List<Enfermidade> findAll() {
        return enfermidadeRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private EnfermidadeEntity mapToEntity(final Enfermidade enfermidade) {
        return EnfermidadeEntity.builder()
                .idEnfermidade(enfermidade.getIdEnfermidade())
                .nomeEnfermidade(enfermidade.getNomeEnfermidade())
                .cid(enfermidade.getCid())
                .build();
    }

    private Enfermidade mapToDomain(final EnfermidadeEntity entity) {
        return Enfermidade.builder()
                .idEnfermidade(entity.getIdEnfermidade())
                .nomeEnfermidade(entity.getNomeEnfermidade())
                .cid(entity.getCid())
                .build();
    }
}