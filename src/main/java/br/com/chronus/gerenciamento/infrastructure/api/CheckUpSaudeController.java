package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.dto.checkup.CheckUpSaudeRequest;
import br.com.chronus.gerenciamento.application.gateway.CheckUpSaudeGateway;
import br.com.chronus.gerenciamento.application.mapper.CheckUpMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("chronus/checkups")
@RequiredArgsConstructor
public class CheckUpSaudeController {

    private final CheckUpSaudeGateway checkUpSaudeGateway;
    private final CheckUpMapper checkUpMapper;

    @PostMapping
    public ResponseEntity<CheckUpSaude> create(@Valid @RequestBody CheckUpSaudeRequest dto) {
        CheckUpSaude created = checkUpSaudeGateway.save(checkUpMapper.toDomain(dto, null));
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckUpSaude> getById(@PathVariable Integer id) {
        Optional<CheckUpSaude> found = checkUpSaudeGateway.findById(id);
        return found.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckUpSaude> update(@PathVariable Integer id,
                                               @Valid @RequestBody CheckUpSaudeRequest dto) {
        CheckUpSaude updated = checkUpSaudeGateway.update(checkUpMapper.toDomain(dto, id));
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        checkUpSaudeGateway.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<CheckUpSaude>> findAll() {
        List<CheckUpSaude> list = checkUpSaudeGateway.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<CheckUpSaude>> findByPaciente(@PathVariable Integer idPaciente) {
        List<CheckUpSaude> list = checkUpSaudeGateway.findByPacienteId(idPaciente);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/profissional/{idProfissionalSaude}")
    public ResponseEntity<List<CheckUpSaude>> findByProfissional(@PathVariable Integer idProfissionalSaude) {
        List<CheckUpSaude> list = checkUpSaudeGateway.findByProfissionalSaudeId(idProfissionalSaude);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
