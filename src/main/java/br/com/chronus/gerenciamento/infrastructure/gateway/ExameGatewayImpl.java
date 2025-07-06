package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ExameEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.ExameRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExameGatewayImpl implements ExameGateway {

    private final ExameRepository exameRepository;

    @Override
    public Exame save(final Exame exame) {
        ExameEntity entity = mapToEntity(exame);
        ExameEntity saved = exameRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Exame> findExameById(final int idExame) {
        return exameRepository.findById(idExame)
                .map(this::mapToDomain);
    }

    @Override
    public Exame update(final Exame exame) {
        ExameEntity found = exameRepository.findById(exame.getIdExame())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Exame não encontrado com o ID [%s]", exame.getIdExame())));
        ExameEntity entity = mapToEntity(exame);
        entity.setIdExame(found.getIdExame());
        ExameEntity updated = exameRepository.save(entity);
        return mapToDomain(updated);
    }

    @Transactional
    @Override
    public void delete(final Integer idExame) {
        ExameEntity entity = exameRepository.findById(idExame)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Exame não encontrado com o ID [%s]", idExame)));
        exameRepository.delete(entity);
    }

    @Override
    public List<Exame> findAll() {
        return exameRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Exame> findExamesByPaciente(final Integer idPaciente) {
        return exameRepository.findByIdPaciente(idPaciente)
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Exame> findExamesByProfissionalSaude(final Integer idProfissionalSaude) {
        return exameRepository.findAll().stream()
                .filter(e -> e.getIdProfissionalSaude().equals(idProfissionalSaude))
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Exame> findExamesByStatus(final EnumStatusExame statusExame) {
        return exameRepository.findByStatusExame(statusExame)
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Exame> findExamesByTipo(final EnumExame tipoExame) {
        return exameRepository.findAll().stream()
                .filter(e -> e.getListaExames() != null && e.getListaExames().contains(tipoExame))
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private ExameEntity mapToEntity(final Exame exame) {
        return ExameEntity.builder()
                .idExame(exame.getIdExame())
                .idPaciente(exame.getIdPaciente())
                .idProfissionalSaude(exame.getIdProfissionalSaude())
                .dataExame(exame.getDataExame())
                .listaExames(exame.getListaExames())
                .statusExame(exame.getStatusExame())
                .build();
    }

    private Exame mapToDomain(final ExameEntity entity) {
        return Exame.builder()
                .idExame(entity.getIdExame())
                .idPaciente(entity.getIdPaciente())
                .idProfissionalSaude(entity.getIdProfissionalSaude())
                .dataExame(entity.getDataExame())
                .listaExames(entity.getListaExames())
                .statusExame(entity.getStatusExame())
                .build();
    }
}