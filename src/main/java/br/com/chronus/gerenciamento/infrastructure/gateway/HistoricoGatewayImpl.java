package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.HistoricoEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.HistoricoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HistoricoGatewayImpl implements HistoricoGateway {

    private final HistoricoRepository historicoRepository;

    public HistoricoGatewayImpl(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    @Override
    public Historico salvar(Historico historico) {
        HistoricoEntity entity = toEntity(historico);
        HistoricoEntity saved = historicoRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Historico> buscarPorId(Integer id) {
        return historicoRepository.findById(id.longValue())
                .map(this::toDomain);
    }

    @Override
    public List<Historico> listarPorPaciente(Integer idPaciente) {
        return historicoRepository.findByIdPaciente(idPaciente.longValue())
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(Integer id) {
        historicoRepository.deleteById(id.longValue());
    }

    private HistoricoEntity toEntity(Historico historico) {
        HistoricoEntity entity = new HistoricoEntity();
        entity.setId(historico.getId() != null ? historico.getId().longValue() : null);
        entity.setIdPaciente(historico.getIdPaciente());
        entity.setObservacoes(historico.getObservacoes());
        entity.setDataInicio(historico.getDataInicio());
        entity.setDataFim(historico.getDataFim());
        entity.setIdCheckup(historico.getIdCheckup());
        return entity;
    }

    private Historico toDomain(HistoricoEntity entity) {
        Historico historico = new Historico();
        historico.setId(entity.getId() != null ? entity.getId().intValue() : null);
        historico.setIdPaciente(entity.getIdPaciente());
        historico.setObservacoes(entity.getObservacoes());
        historico.setDataInicio(entity.getDataInicio());
        historico.setDataFim(entity.getDataFim());
        historico.setIdCheckup(entity.getIdCheckup());
        return historico;
    }
}