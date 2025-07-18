
package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.dto.historico.HistoricoRequestDto;
import br.com.chronus.gerenciamento.application.mapper.HistoricoMapper;
import br.com.chronus.gerenciamento.application.usecase.historico.BuscarHistoricoPorIdUseCase;
import br.com.chronus.gerenciamento.application.usecase.historico.BuscarTodosHistoricosUseCase;
import br.com.chronus.gerenciamento.application.usecase.historico.DeletarHistoricoPorIdUseCase;
import br.com.chronus.gerenciamento.application.usecase.historico.ListarHistoricoPorPacienteUseCase;
import br.com.chronus.gerenciamento.application.usecase.historico.SalvarHistoricoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("chronus/historicos")
@RequiredArgsConstructor
public class HistoricoController {

    private final SalvarHistoricoUseCase salvarHistoricoUseCase;
    private final BuscarHistoricoPorIdUseCase buscarHistoricoPorIdUseCase;
    private final ListarHistoricoPorPacienteUseCase listarHistoricoPorPacienteUseCase;
    private final DeletarHistoricoPorIdUseCase deletarHistoricoPorIdUseCase;
    private final BuscarTodosHistoricosUseCase buscarTodosHistoricosUseCase;
    private final HistoricoMapper historicoMapper;

    @PostMapping
    public ResponseEntity<Historico> salvar(@Valid @RequestBody HistoricoRequestDto historicoRequestDto) {
        Historico created = salvarHistoricoUseCase.executar(historicoMapper.toDomain(historicoRequestDto));
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historico> buscarPorId(@PathVariable Integer id) {
        return buscarHistoricoPorIdUseCase.executar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Historico> editar(@PathVariable Integer id,
                                            @Valid @RequestBody HistoricoRequestDto historicoRequestDto) {
        Historico historico = historicoMapper.toDomain(historicoRequestDto);
        historico.setId(id);
        return ResponseEntity.ok(salvarHistoricoUseCase.executar(historico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarHistoricoPorIdUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Historico>> listarTodos() {
        return ResponseEntity.ok(buscarTodosHistoricosUseCase.executar());
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Historico>> listarPorPaciente(@PathVariable Integer idPaciente) {
        return ResponseEntity.ok(listarHistoricoPorPacienteUseCase.executar(idPaciente));
    }

}