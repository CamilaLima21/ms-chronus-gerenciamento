package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import br.com.chronus.gerenciamento.application.mapper.TratamentoMapper;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.TratamentoEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.TratamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TratamentoGatewayImpl implements TratamentoGateway {

    private final TratamentoRepository tratamentoRepository;
    private final TratamentoMapper tratamentoMapper;

    @Override
    public Tratamento save(Tratamento tratamento) {
        TratamentoEntity tratamentoEntity = tratamentoMapper.mapToEntity(tratamento);
        TratamentoEntity savedEntity = tratamentoRepository.save(tratamentoEntity);
        return tratamentoMapper.mapToDomain(savedEntity);
    }

    @Override
    public Optional<Tratamento> findById(Integer idTratamento) {
        return tratamentoRepository.findById(idTratamento)
                .map(tratamentoMapper::mapToDomain);
    }

    @Override
    public Tratamento update(Tratamento tratamento) {
        TratamentoEntity found = tratamentoRepository.findById(tratamento.getIdTratamento())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Tratamento não encontrado com o ID [%s]", tratamento.getIdTratamento())));
        TratamentoEntity entity = tratamentoMapper.mapToEntity(tratamento);
        entity.setIdTratamento(found.getIdTratamento());
        TratamentoEntity updated = tratamentoRepository.save(entity);
        return tratamentoMapper.mapToDomain(updated);
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
                .map(tratamentoMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Tratamento> findByPacienteId(final Integer idPaciente) {
        return tratamentoRepository.findByIdPaciente(idPaciente)
                .stream()
                .map(tratamentoMapper::mapToDomain)
                .collect(Collectors.toList());
    }

}

