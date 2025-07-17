package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.dto.historico.HistoricoRequestDto;
import br.com.chronus.gerenciamento.application.usecase.checkup.FindCheckUpSaudeById;
import br.com.chronus.gerenciamento.application.usecase.consulta.GetConsulta;
import br.com.chronus.gerenciamento.application.usecase.enfermidade.GetEnfermidade;
import br.com.chronus.gerenciamento.application.usecase.tratamento.GetTratamentoByIdUseCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HistoricoMapper {

    private final GetEnfermidade getEnfermidade;
    private final GetTratamentoByIdUseCase getTratamento;
    private final GetConsulta getConsulta;
    private final FindCheckUpSaudeById getCheckUpSaude;

    public HistoricoMapper(
            GetEnfermidade getEnfermidade,
            GetTratamentoByIdUseCase getTratamento,
            GetConsulta getConsulta,
            FindCheckUpSaudeById getCheckUpSaude
    ) {
        this.getEnfermidade = getEnfermidade;
        this.getTratamento = getTratamento;
        this.getConsulta = getConsulta;
        this.getCheckUpSaude = getCheckUpSaude;
    }

    public Historico toDomain(HistoricoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Historico historico = new Historico();
        historico.setIdPaciente(dto.getIdPaciente());
        historico.setEnfermidades(mapEnfermidades(dto.getIdEnfermidades()));
        historico.setTratamentos(mapTratamentos(dto.getIdTratamentos()));
        historico.setConsultas(mapConsultas(dto.getIdConsultas()));
        historico.setCheckups(mapCheckups(dto.getIdCheckups()));
        historico.setObservacoes(dto.getObservacoes());
        historico.setDataInicio(dto.getDataInicio());
        historico.setDataFim(dto.getDataFim());

        return historico;
    }

    private List<Enfermidade> mapEnfermidades(List<Integer> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(getEnfermidade::execute)
                .collect(Collectors.toList());
    }

    private List<Tratamento> mapTratamentos(List<Integer> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(getTratamento::execute)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<Consulta> mapConsultas(List<Integer> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(getConsulta::execute)
                .collect(Collectors.toList());
    }

    private List<CheckUpSaude> mapCheckups(List<Integer> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(getCheckUpSaude::execute)
                .collect(Collectors.toList());
    }

}

