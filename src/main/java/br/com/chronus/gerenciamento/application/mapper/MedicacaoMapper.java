package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.dto.medicacao.UpdateMedicacaoRequest;
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
}