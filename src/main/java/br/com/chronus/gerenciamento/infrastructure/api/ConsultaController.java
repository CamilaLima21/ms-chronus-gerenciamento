package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.dto.consulta.ConsultaRequest;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.mapper.ConsultaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("chronus/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaGateway consultaGateway;
    private final ConsultaMapper consultaMapper;

    @PostMapping
    public ResponseEntity<Consulta> createConsulta(@Validated @RequestBody ConsultaRequest consultaRequest) {
        Consulta createdConsulta = consultaGateway.createConsulta(consultaMapper.toDomain(consultaRequest, null));
        return new ResponseEntity<>(createdConsulta, HttpStatus.CREATED);
    }

    @GetMapping("/{idConsulta}")
    public ResponseEntity<Consulta> getConsultaById(@PathVariable Integer idConsulta) {
        Optional<Consulta> consulta = consultaGateway.getConsultaById(idConsulta);
        return consulta.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{idConsulta}")
    public ResponseEntity<Consulta> updateConsulta(@PathVariable Integer idConsulta, @Validated @RequestBody ConsultaRequest consultaRequest) {
        consultaRequest.setIdConsulta(idConsulta);
        Consulta updatedConsulta = consultaGateway.updateConsulta(consultaMapper.toDomain(consultaRequest, idConsulta));
        return new ResponseEntity<>(updatedConsulta, HttpStatus.OK);
    }

    @DeleteMapping("/{idConsulta}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable int idConsulta) {
        consultaGateway.deleteConsulta(idConsulta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}