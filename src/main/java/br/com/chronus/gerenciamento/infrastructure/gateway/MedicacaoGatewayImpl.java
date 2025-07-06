package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.gateway.MedicacaoGateway;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.MedicacaoEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.MedicacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MedicacaoGatewayImpl implements MedicacaoGateway {

    private final MedicacaoRepository medicacaoRepository;

    @Override
    public Medicacao save(final Medicacao medicacao) {
        MedicacaoEntity entity = mapToEntity(medicacao);
        MedicacaoEntity saved = medicacaoRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Medicacao> findMedicacaoById(final int idMedicacao) {
        return medicacaoRepository.findById(idMedicacao)
                .map(this::mapToDomain);
    }

    @Override
    public Medicacao update(final Medicacao medicacao) {
        MedicacaoEntity found = medicacaoRepository.findById(medicacao.getIdMedicacao())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Medicação não encontrada com o ID [%s]", medicacao.getIdMedicacao())));
        MedicacaoEntity entity = mapToEntity(medicacao);
        entity.setIdMedicacao(found.getIdMedicacao());
        MedicacaoEntity updated = medicacaoRepository.save(entity);
        return mapToDomain(updated);
    }

    @Transactional
    @Override
    public void delete(final Integer idMedicacao) {
        MedicacaoEntity entity = medicacaoRepository.findById(idMedicacao)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Medicação não encontrada com o ID [%s]", idMedicacao)));
        medicacaoRepository.delete(entity);
    }

    @Override
    public List<Medicacao> findAll() {
        return medicacaoRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Medicacao> findMedicacaoBySigtap(final String sigtapMedicacao) {
        return medicacaoRepository.findBySigtapMedicacao(sigtapMedicacao)
                .map(this::mapToDomain);
    }

    @Override
    public Optional<Medicacao> findMedicacaoByNome(final String nomeMedicacao) {
        return medicacaoRepository.findByNomeMedicacao(nomeMedicacao)
                .map(this::mapToDomain);
    }

    private MedicacaoEntity mapToEntity(final Medicacao medicacao) {
        return MedicacaoEntity.builder()
                .idMedicacao(medicacao.getIdMedicacao())
                .nomeMedicacao(medicacao.getNomeMedicacao())
                .descricaoMedicacao(medicacao.getDescricaoMedicacao())
                .sigtapMedicacao(medicacao.getSigtapMedicacao())
                .build();
    }

    private Medicacao mapToDomain(final MedicacaoEntity entity) {
        return Medicacao.builder()
                .idMedicacao(entity.getIdMedicacao())
                .nomeMedicacao(entity.getNomeMedicacao())
                .descricaoMedicacao(entity.getDescricaoMedicacao())
                .sigtapMedicacao(entity.getSigtapMedicacao())
                .build();
    }
}