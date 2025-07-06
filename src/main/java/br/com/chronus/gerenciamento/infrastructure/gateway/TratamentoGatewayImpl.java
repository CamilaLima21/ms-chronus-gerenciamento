package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.TratamentoEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.TratamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TratamentoGatewayImpl implements TratamentoGateway {

    private final TratamentoRepository tratamentoRepository;

    @Override
    public Tratamento save(Tratamento tratamento) {
        TratamentoEntity tratamentoEntity = mapToEntity(tratamento);
        TratamentoEntity savedEntity = tratamentoRepository.save(tratamentoEntity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Optional<Tratamento> findById(Integer idTratamento) {
        return tratamentoRepository.findById(idTratamento)
                .map(this::mapToDomain);
    }

    @Override
    public Tratamento update(Tratamento tratamento) {
        TratamentoEntity found = tratamentoRepository.findById(tratamento.getIdTratamento())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Tratamento não encontrado com o ID [%s]", tratamento.getIdTratamento())));
        TratamentoEntity entity = mapToEntity(tratamento);
        entity.setIdTratamento(found.getIdTratamento());
        TratamentoEntity updated = tratamentoRepository.save(entity);
        return mapToDomain(updated);
    }

    @Transactional
    @Override
    public void delete(final Integer idTratamento) {
        TratamentoEntity entity = tratamentoRepository.findById(idTratamento)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Tratamento não encontrado com o ID [%s]", idTratamento)));
        tratamentoRepository.delete(entity);
    }

    @Override
    public List<Tratamento> findAll() {
        return tratamentoRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private TratamentoEntity mapToEntity(Tratamento tratamento) {
        return TratamentoEntity.builder()
                .idTratamento(tratamento.getIdTratamento())
                .medicamento(tratamento.getIdMedicamento())
                .inicioTratamento(tratamento.getInicioTratamento())
                .fimTratamento(tratamento.getFimTratamento())
                .periodicidade(tratamento.getPeriodicidade())
                .dosagem(tratamento.getDosagem())
                .horarios(tratamento.getHorarios())
                .build();
    }

    private Tratamento mapToDomain(TratamentoEntity entity) {
        return Tratamento.builder()
                .idTratamento(entity.getIdTratamento())
                .idMedicamento(entity.getMedicamento())
                .inicioTratamento(entity.getInicioTratamento())
                .fimTratamento(entity.getFimTratamento())
                .periodicidade(entity.getPeriodicidade())
                .dosagem(entity.getDosagem())
                .horarios(entity.getHorarios())
                .build();
    }
}

