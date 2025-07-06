package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.usecase.historico.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historicos")
public class HistoricoController {

    private final SalvarHistoricoUseCase salvarHistoricoUseCase;
    private final BuscarHistoricoPorIdUseCase buscarHistoricoPorIdUseCase;
    private final ListarHistoricoPorPacienteUseCase listarHistoricoPorPacienteUseCase;
    private final DeletarHistoricoPorIdUseCase deletarHistoricoPorIdUseCase;

    public HistoricoController(
            SalvarHistoricoUseCase salvarHistoricoUseCase,
            BuscarHistoricoPorIdUseCase buscarHistoricoPorIdUseCase,
            ListarHistoricoPorPacienteUseCase listarHistoricoPorPacienteUseCase,
            DeletarHistoricoPorIdUseCase deletarHistoricoPorIdUseCase) {
        this.salvarHistoricoUseCase = salvarHistoricoUseCase;
        this.buscarHistoricoPorIdUseCase = buscarHistoricoPorIdUseCase;
        this.listarHistoricoPorPacienteUseCase = listarHistoricoPorPacienteUseCase;
        this.deletarHistoricoPorIdUseCase = deletarHistoricoPorIdUseCase;
    }

    @PostMapping
    public ResponseEntity<Historico> salvar(@RequestBody Historico historico) {
        return ResponseEntity.ok(salvarHistoricoUseCase.executar(historico));
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