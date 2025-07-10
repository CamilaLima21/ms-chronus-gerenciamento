package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.dto.historico.CreateHistoricoRequestDto;
import br.com.chronus.gerenciamento.application.mapper.HistoricoMapper;
import br.com.chronus.gerenciamento.application.usecase.historico.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chronus/historicos")
@RequiredArgsConstructor
public class HistoricoController {

    private final SalvarHistoricoUseCase salvarHistoricoUseCase;
    private final BuscarHistoricoPorIdUseCase buscarHistoricoPorIdUseCase;
    private final ListarHistoricoPorPacienteUseCase listarHistoricoPorPacienteUseCase;
    private final DeletarHistoricoPorIdUseCase deletarHistoricoPorIdUseCase;

    @PostMapping
    public ResponseEntity<Historico> salvar(@Validated @RequestBody CreateHistoricoRequestDto createHistoricoRequestDto) {
        Historico historico = HistoricoMapper.toDomain(createHistoricoRequestDto);
        Historico created = salvarHistoricoUseCase.executar(historico);
        return ResponseEntity.ok(salvarHistoricoUseCase.executar(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historico> buscarPorId(@PathVariable Integer id) {
        return buscarHistoricoPorIdUseCase.executar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Historico>> listarPorPaciente(@PathVariable Integer idPaciente) {
        return ResponseEntity.ok(listarHistoricoPorPacienteUseCase.executar(idPaciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarHistoricoPorIdUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}