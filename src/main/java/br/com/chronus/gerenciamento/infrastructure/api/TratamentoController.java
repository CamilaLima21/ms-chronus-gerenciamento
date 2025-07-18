package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Tratamento;
import br.com.chronus.gerenciamento.application.gateway.TratamentoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("chronus/tratamentos")
@RequiredArgsConstructor
public class TratamentoController {

    private final TratamentoGateway tratamentoGateway;

    @PostMapping
    public ResponseEntity<Tratamento> createTratamento(@Validated @RequestBody Tratamento tratamento) {
        Tratamento created = tratamentoGateway.save(tratamento);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{idTratamento}")
    public ResponseEntity<Tratamento> getTratamentoById(@PathVariable Integer idTratamento) {
        Optional<Tratamento> tratamento = tratamentoGateway.findById(idTratamento);
        return tratamento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{idTratamento}")
    public ResponseEntity<Tratamento> updateTratamento(@PathVariable Integer idTratamento, @Validated @RequestBody Tratamento tratamento) {
        tratamento.setIdTratamento(idTratamento);
        Tratamento updated = tratamentoGateway.update(tratamento);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{idTratamento}")
    public ResponseEntity<Void> deleteTratamento(@PathVariable int idTratamento) {
        tratamentoGateway.delete(idTratamento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Tratamento>> getAllTratamentos() {
        List<Tratamento> list = tratamentoGateway.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Tratamento>> getTratamentosByPacienteId(@PathVariable Integer idPaciente) {
        List<Tratamento> tratamentos = tratamentoGateway.findByPacienteId(idPaciente);
        if (tratamentos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tratamentos, HttpStatus.OK);
    }


}