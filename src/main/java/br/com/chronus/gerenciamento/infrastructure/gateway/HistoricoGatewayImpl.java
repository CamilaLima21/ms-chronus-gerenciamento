package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.gateway.HistoricoGateway;
import br.com.chronus.gerenciamento.application.mapper.CheckUpMapper;
import br.com.chronus.gerenciamento.application.mapper.ConsultaMapper;
import br.com.chronus.gerenciamento.application.mapper.EnfermidadeMapper;
import br.com.chronus.gerenciamento.application.mapper.TratamentoMapper;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.HistoricoEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.HistoricoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HistoricoGatewayImpl implements HistoricoGateway {

    private final HistoricoRepository historicoRepository;
    private final EnfermidadeMapper enfermidadeMapper;
    private final TratamentoMapper tratamentoMapper;
    private final ConsultaMapper consultaMapper;
    private final CheckUpMapper checkupMapper;

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

    @Override
    public List<Historico> buscarTodos() {
        return historicoRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }


    private HistoricoEntity toEntity(Historico historico) {
        HistoricoEntity entity = new HistoricoEntity();
        entity.setId(historico.getId() != null ? historico.getId().longValue() : null);
        entity.setIdPaciente(historico.getIdPaciente());
        entity.setEnfermidades(
                historico.getEnfermidades() != null
                        ? historico.getEnfermidades().stream()
                        .map(enfermidadeMapper::toEnfermidadeEntity)
                        .collect(Collectors.toList())
                        : null
        );
        entity.setTratamentos(
                historico.getTratamentos() != null
                        ? historico.getTratamentos().stream()
                        .map(tratamentoMapper::mapToEntity)
                        .collect(Collectors.toList())
                        : null
        );
        entity.setConsultas(
                historico.getConsultas() != null
                        ? historico.getConsultas().stream()
                        .map(consultaMapper::toConsultaEntity)
                        .collect(Collectors.toList())
                        : null
        );
        entity.setCheckups(
                historico.getCheckups() != null
                        ? historico.getCheckups().stream()
                        .map(checkupMapper::mapToEntity)
                        .collect(Collectors.toList())
                        : null
        );
        entity.setObservacoes(historico.getObservacoes());
        entity.setDataInicio(historico.getDataInicio());
        entity.setDataFim(historico.getDataFim());
        return entity;
    }

    private Historico toDomain(HistoricoEntity entity) {
        Historico historico = new Historico();
        historico.setId(entity.getId() != null ? entity.getId().intValue() : null);
        historico.setIdPaciente(entity.getIdPaciente());
        historico.setEnfermidades(
                entity.getEnfermidades() != null
                        ? entity.getEnfermidades().stream()
                        .map(enfermidadeMapper::toEnfermidade)
                        .collect(Collectors.toList())
                        : null
        );
        historico.setTratamentos(
                entity.getTratamentos() != null
                        ? entity.getTratamentos().stream()
                        .map(tratamentoMapper::mapToDomain)
                        .collect(Collectors.toList())
                        : null
        );
        historico.setConsultas(
                entity.getConsultas() != null
                        ? entity.getConsultas().stream()
                        .map(consultaMapper::toConsulta)
                        .collect(Collectors.toList())
                        : null
        );
        historico.setCheckups(
                entity.getCheckups() != null
                        ? entity.getCheckups().stream()
                        .map(checkupMapper::mapToDomain)
                        .collect(Collectors.toList())
                        : null
        );
        historico.setObservacoes(entity.getObservacoes());
        historico.setDataInicio(entity.getDataInicio());
        historico.setDataFim(entity.getDataFim());
        return historico;
    }
}