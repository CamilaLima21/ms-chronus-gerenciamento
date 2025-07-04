package br.com.chronus.gerenciamento.infrastructure.gateway;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.ConsultaEntity;
import br.com.chronus.gerenciamento.infrastructure.persistence.repository.ConsultaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ConsultaGatewayImpl implements ConsultaGateway {

    private final ConsultaRepository consultaRepository;

    @Override
    public Consulta createConsulta(Consulta consulta) {
        ConsultaEntity entity = mapToEntity(consulta);
        ConsultaEntity saved = consultaRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Consulta> getConsultaById(int idConsulta) {
        return consultaRepository.findById(idConsulta)
                .map(this::mapToDomain);
    }

    @Override
    public Consulta updateConsulta(Consulta consulta) {
        ConsultaEntity found = consultaRepository.findById(consulta.getIdConsulta())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Consulta não encontrada com o ID [%s]", consulta.getIdConsulta())));
        ConsultaEntity entity = mapToEntity(consulta);
        entity.setIdConsulta(found.getIdConsulta());
        ConsultaEntity updated = consultaRepository.save(entity);
        return mapToDomain(updated);
    }

    @Transactional
    @Override
    public void deleteConsulta(int idConsulta) {
        ConsultaEntity entity = consultaRepository.findById(idConsulta)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Consulta não encontrada com o ID [%s]", idConsulta)));
        consultaRepository.delete(entity);
    }

    private ConsultaEntity mapToEntity(Consulta consulta) {
        return ConsultaEntity.builder()
                .idConsulta(consulta.getIdConsulta())
                .idPaciente(consulta.getIdPaciente())
                .idProfissionalSaude(consulta.getIdProfissionalSaude())
                .dataHoraConsulta(consulta.getDataHoraConsulta())
                .observacaoConsulta(consulta.getObservacaoConsulta())
                .statusConsulta(consulta.getStatusConsulta())
                .tipoConsulta(consulta.getTipoConsulta())
                .motivoCancelamento(consulta.getMotivoCancelamento())
                .build();
    }

    private Consulta mapToDomain(ConsultaEntity entity) {
        return Consulta.builder()
                .idConsulta(entity.getIdConsulta())
                .idPaciente(entity.getIdPaciente())
                .idProfissionalSaude(entity.getIdProfissionalSaude())
                .dataHoraConsulta(entity.getDataHoraConsulta())
                .observacaoConsulta(entity.getObservacaoConsulta())
                .statusConsulta(entity.getStatusConsulta())
                .tipoConsulta(entity.getTipoConsulta())
                .motivoCancelamento(entity.getMotivoCancelamento())
                .build();
    }
}