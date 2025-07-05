package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.gateway.EnfermidadeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("chronus/enfermidades")
@RequiredArgsConstructor
public class EnfermidadeController {

    private final EnfermidadeGateway enfermidadeGateway;

    @PostMapping
    public ResponseEntity<Enfermidade> createEnfermidade(@Validated @RequestBody Enfermidade enfermidade) {
        Enfermidade created = enfermidadeGateway.save(enfermidade);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{idEnfermidade}")
    public ResponseEntity<Enfermidade> getEnfermidadeById(@PathVariable Integer idEnfermidade) {
        Optional<Enfermidade> enfermidade = enfermidadeGateway.findEnfermidadeById(idEnfermidade);
        return enfermidade.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{idEnfermidade}")
    public ResponseEntity<Enfermidade> updateEnfermidade(@PathVariable Integer idEnfermidade, @Validated @RequestBody Enfermidade enfermidade) {
        enfermidade.setIdEnfermidade(idEnfermidade);
        Enfermidade updated = enfermidadeGateway.update(enfermidade);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{idEnfermidade}")
    public ResponseEntity<Void> deleteEnfermidade(@PathVariable int idEnfermidade) {
        enfermidadeGateway.delete(idEnfermidade);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Enfermidade>> getAllEnfermidades() {
        List<Enfermidade> list = enfermidadeGateway.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}