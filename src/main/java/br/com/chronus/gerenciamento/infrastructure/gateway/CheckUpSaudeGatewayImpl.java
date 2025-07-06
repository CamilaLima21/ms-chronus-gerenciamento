package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
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

    @Override
    public CheckUpSaude save(final CheckUpSaude checkUpSaude) {
        CheckUpSaudeEntity entity = mapToEntity(checkUpSaude);
        CheckUpSaudeEntity saved = repository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<CheckUpSaude> findById(final Integer idCheckUpSaude) {
        return repository.findById(idCheckUpSaude)
                .map(this::mapToDomain);
    }

    @Override
    public CheckUpSaude update(final CheckUpSaude checkUpSaude) {
        CheckUpSaudeEntity found = repository.findById(checkUpSaude.getIdCheckUpsaude())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Check-up de saúde não encontrado com o ID [%s]", checkUpSaude.getIdCheckUpsaude())));
        CheckUpSaudeEntity entity = mapToEntity(checkUpSaude);
        entity.setIdCheckUpsaude(found.getIdCheckUpsaude());
        CheckUpSaudeEntity updated = repository.save(entity);
        return mapToDomain(updated);
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
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckUpSaude> findByPacienteId(final Integer idPaciente) {
        return repository.findByIdPaciente(idPaciente)
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckUpSaude> findByProfissionalSaudeId(final Integer idProfissionalSaude) {
        return repository.findByIdProfissionalSaude(idProfissionalSaude)
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }


    private CheckUpSaudeEntity mapToEntity(final CheckUpSaude domain) {
        return CheckUpSaudeEntity.builder()
                .idCheckUpsaude(domain.getIdCheckUpsaude())
                .idPaciente(domain.getIdPaciente())
                .idProfissionalSaude(domain.getIdProfissionalSaude())
                .glicemia(domain.getGlicemia())
                .pressaoArterial(domain.getPressaoArterial())
                .frequenciaCardiaca(domain.getFrequenciaCardiaca())
                .frequenciaRespiratoria(domain.getFrequenciaRespiratoria())
                .temperaturaCorporal(domain.getTemperaturaCorporal())
                .saturacaoOxigenio(domain.getSaturacaoOxigenio())
                .outrosDados(domain.getOutrosDados())
                .observacoes(domain.getObservacoes())
                .dataHoraRegistro(domain.getDataHoraRegistro())
                .build();
    }


    private CheckUpSaude mapToDomain(final CheckUpSaudeEntity entity) {
        return CheckUpSaude.builder()
                .idCheckUpsaude(entity.getIdCheckUpsaude())
                .idPaciente(entity.getIdPaciente())
                .idProfissionalSaude(entity.getIdProfissionalSaude())
                .glicemia(entity.getGlicemia())
                .pressaoArterial(entity.getPressaoArterial())
                .frequenciaCardiaca(entity.getFrequenciaCardiaca())
                .frequenciaRespiratoria(entity.getFrequenciaRespiratoria())
                .temperaturaCorporal(entity.getTemperaturaCorporal())
                .saturacaoOxigenio(entity.getSaturacaoOxigenio())
                .outrosDados(entity.getOutrosDados())
                .observacoes(entity.getObservacoes())
                .dataHoraRegistro(entity.getDataHoraRegistro())
                .build();
    }
}
