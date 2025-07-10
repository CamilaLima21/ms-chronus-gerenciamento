package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.dto.historico.CreateHistoricoRequestDto;

public class HistoricoMapper {
    public static Historico toDomain(CreateHistoricoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Historico historico = new Historico();
        historico.setIdPaciente(dto.getIdPaciente());
        historico.setEnfermidades(dto.getIdEnfermidade());
        historico.setMedicamentos(dto.getIdMedicacao());
        historico.setTratamento(dto.getIdTratamento());
        historico.setConsulta(dto.getIdConsulta());
        historico.setIdCheckup(dto.getIdCheckup());
        historico.setObservacoes(dto.getObservacoes());
        historico.setDataInicio(dto.getDataInicio());
        historico.setDataFim(dto.getDataFim());

        return historico;
    }
}

