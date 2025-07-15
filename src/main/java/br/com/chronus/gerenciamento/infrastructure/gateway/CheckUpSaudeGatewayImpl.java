package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.mapper.CheckUpMapper;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.CheckUpSaudeEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.CheckUpSaudeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CheckUpSaudeGatewayImpl implements CheckUpSaudeGateway {

    private final CheckUpSaudeRepository repository;
    private final CheckUpMapper mapper;

    @Override
    public CheckUpSaude save(final CheckUpSaude checkUpSaude) {
        CheckUpSaudeEntity entity = mapper.mapToEntity(checkUpSaude);
        CheckUpSaudeEntity saved = repository.save(entity);
        return mapper.mapToDomain(saved);
    }

    @Override
    public Optional<CheckUpSaude> findById(final Integer idCheckUpSaude) {
        return repository.findById(idCheckUpSaude)
                .map(mapper::mapToDomain);
    }

    @Override
    public CheckUpSaude update(final CheckUpSaude checkUpSaude) {
        CheckUpSaudeEntity found = repository.findById(checkUpSaude.getIdCheckUpsaude())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Check-up de saúde não encontrado com o ID [%s]", checkUpSaude.getIdCheckUpsaude())));
        CheckUpSaudeEntity entity = mapper.mapToEntity(checkUpSaude);
        entity.setIdCheckUpsaude(found.getIdCheckUpsaude());
        CheckUpSaudeEntity updated = repository.save(entity);
        return mapper.mapToDomain(updated);
    }

    @Transactional
    @Override
    public void delete(final Integer idCheckUpSaude) {
        CheckUpSaudeEntity entity = repository.findById(idCheckUpSaude)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Check-up de saúde não encontrado com o ID [%s]", idCheckUpSaude)));
        repository.delete(entity);
    }

    @Override
    public List<CheckUpSaude> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckUpSaude> findByPacienteId(final Integer idPaciente) {
        return repository.findByIdPaciente(idPaciente)
                .stream()
                .map(mapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckUpSaude> findByProfissionalSaudeId(final Integer idProfissionalSaude) {
        return repository.findByIdProfissionalSaude(idProfissionalSaude)
                .stream()
                .map(mapper::mapToDomain)
                .collect(Collectors.toList());
    }

}
