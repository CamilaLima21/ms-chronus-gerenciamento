package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("chronus/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaGateway consultaGateway;

    @PostMapping
    public ResponseEntity<Consulta> createConsulta(@Validated @RequestBody Consulta consulta) {
        Consulta createdConsulta = consultaGateway.createConsulta(consulta);
        return new ResponseEntity<>(createdConsulta, HttpStatus.CREATED);
    }

    @GetMapping("/{idConsulta}")
    public ResponseEntity<Consulta> getConsultaById(@PathVariable Integer idConsulta) {
        Optional<Consulta> consulta = consultaGateway.getConsultaById(idConsulta);
        return consulta.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{idConsulta}")
    public ResponseEntity<Consulta> updateConsulta(@PathVariable Integer idConsulta, @Validated @RequestBody Consulta consulta) {
        consulta.setIdConsulta(idConsulta);
        Consulta updatedConsulta = consultaGateway.updateConsulta(consulta);
        return new ResponseEntity<>(updatedConsulta, HttpStatus.OK);
    }

    @DeleteMapping("/{idConsulta}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable int idConsulta) {
        consultaGateway.deleteConsulta(idConsulta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}