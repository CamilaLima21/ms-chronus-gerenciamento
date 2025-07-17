
package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Historico;
import br.com.chronus.gerenciamento.application.dto.historico.HistoricoRequestDto;
import br.com.chronus.gerenciamento.application.mapper.HistoricoMapper;
import br.com.chronus.gerenciamento.application.usecase.historico.BuscarHistoricoPorIdUseCase;
import br.com.chronus.gerenciamento.application.usecase.historico.DeletarHistoricoPorIdUseCase;
import br.com.chronus.gerenciamento.application.usecase.historico.ListarHistoricoPorPacienteUseCase;
import br.com.chronus.gerenciamento.application.usecase.historico.SalvarHistoricoUseCase;
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
    private final HistoricoMapper historicoMapper;

    @PostMapping
    public ResponseEntity<Historico> salvar(@Validated @RequestBody HistoricoRequestDto historicoRequestDto) {
        Historico historico = historicoMapper.toDomain(historicoRequestDto);
        Historico created = salvarHistoricoUseCase.executar(historico);
        return ResponseEntity.ok(salvarHistoricoUseCase.executar(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historico> buscarPorId(@PathVariable Integer id) {
        return buscarHistoricoPorIdUseCase.executar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*@GetMapping
    public ResponseEntity<List<CheckUpSaude>> findAll() {
        List<CheckUpSaude> list = checkUpSaudeGateway.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }*/

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