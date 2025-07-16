package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.dto.medicacao.UpdateMedicacaoRequest;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.MedicacaoEntity;
import org.springframework.stereotype.Component;

@Component
public class MedicacaoMapper {

    public Medicacao toDomain(UpdateMedicacaoRequest dto, Integer id) {
        return Medicacao.builder()
                .idMedicacao(id)
                .nomeMedicacao(dto.getNomeMedicacao())
                .descricaoMedicacao(dto.getDescricaoMedicacao())
                .sigtapMedicacao(dto.getSigtapMedicacao())
                .build();
    }

    public MedicacaoEntity mapMedicamentoToEntity(Medicacao medicacao) {
        if (medicacao == null) return null;
        return MedicacaoEntity.builder()
                .idMedicacao(medicacao.getIdMedicacao())
                .nomeMedicacao(medicacao.getNomeMedicacao())
                .descricaoMedicacao(medicacao.getDescricaoMedicacao())
                .sigtapMedicacao(medicacao.getSigtapMedicacao())
                .build();
    }

    public Medicacao mapEntityToMedicamento(MedicacaoEntity entity) {
        if (entity == null) return null;
        return Medicacao.builder()
                .idMedicacao(entity.getIdMedicacao())
                .nomeMedicacao(entity.getNomeMedicacao())
                .descricaoMedicacao(entity.getDescricaoMedicacao())
                .sigtapMedicacao(entity.getSigtapMedicacao())
                .build();
    }
}