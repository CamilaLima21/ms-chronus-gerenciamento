package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.dto.UpdateExameRequest;
import br.com.chronus.gerenciamento.application.enums.EnumExame;
import br.com.chronus.gerenciamento.application.enums.EnumStatusExame;
import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("chronus/exames")
@RequiredArgsConstructor
public class ExameController {

    private final ExameGateway exameGateway;

    @PostMapping
    public ResponseEntity<Exame> createExame(@Validated @RequestBody UpdateExameRequest dto) {
        Exame created = exameGateway.save(mapToDomain(dto, null));
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{idExame}")
    public ResponseEntity<Exame> getExameById(@PathVariable Integer idExame) {
        Optional<Exame> exame = exameGateway.findExameById(idExame);
        return exame.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{idExame}")
    public ResponseEntity<Exame> updateExame(@PathVariable Integer idExame, @Validated @RequestBody UpdateExameRequest dto) {
        Exame updated = exameGateway.update(mapToDomain(dto, idExame));
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{idExame}")
    public ResponseEntity<Void> deleteExame(@PathVariable int idExame) {
        exameGateway.delete(idExame);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Exame>> getAllExames() {
        List<Exame> list = exameGateway.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Exame>> getExamesByPaciente(@PathVariable Integer idPaciente) {
        List<Exame> exames = exameGateway.findExamesByPaciente(idPaciente);
        return new ResponseEntity<>(exames, HttpStatus.OK);
    }

    @GetMapping("/profissional/{idProfissionalSaude}")
    public ResponseEntity<List<Exame>> getExamesByProfissional(@PathVariable Integer idProfissionalSaude) {
        List<Exame> exames = exameGateway.findExamesByProfissionalSaude(idProfissionalSaude);
        return new ResponseEntity<>(exames, HttpStatus.OK);
    }

    @GetMapping("/status/{statusExame}")
    public ResponseEntity<List<Exame>> getExamesByStatus(@PathVariable EnumStatusExame statusExame) {
        List<Exame> exames = exameGateway.findExamesByStatus(statusExame);
        return new ResponseEntity<>(exames, HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipoExame}")
    public ResponseEntity<List<Exame>> getExamesByTipo(@PathVariable EnumExame tipoExame) {
        List<Exame> exames = exameGateway.findExamesByTipo(tipoExame);
        return new ResponseEntity<>(exames, HttpStatus.OK);
    }

    private Exame mapToDomain(UpdateExameRequest dto, Integer id) {
        return Exame.builder()
                .idExame(id)
                .idPaciente(dto.getIdPaciente())
                .idProfissionalSaude(dto.getIdProfissionalSaude())
                .dataExame(dto.getDataExame())
                .listaExames(dto.getListaExames())
                .statusExame(dto.getStatusExame())
                .build();
    }
}