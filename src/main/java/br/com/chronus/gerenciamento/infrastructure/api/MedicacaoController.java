package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.dto.medicacao.UpdateMedicacaoRequest;
import br.com.chronus.gerenciamento.application.gateway.MedicacaoGateway;
import br.com.chronus.gerenciamento.application.mapper.MedicacaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("chronus/medicacoes")
@RequiredArgsConstructor
public class MedicacaoController {

    private final MedicacaoGateway medicacaoGateway;
    private final MedicacaoMapper medicacaoMapper;

    @PostMapping
    public ResponseEntity<Medicacao> createMedicacao(@Validated @RequestBody UpdateMedicacaoRequest dto) {
        Medicacao created = medicacaoGateway.save(medicacaoMapper.toDomain(dto, null));
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{idMedicacao}")
    public ResponseEntity<Medicacao> getMedicacaoById(@PathVariable Integer idMedicacao) {
        Optional<Medicacao> medicacao = medicacaoGateway.findMedicacaoById(idMedicacao);
        return medicacao.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{idMedicacao}")
    public ResponseEntity<Medicacao> updateMedicacao(@PathVariable Integer idMedicacao, @Validated @RequestBody UpdateMedicacaoRequest dto) {
        Medicacao updated = medicacaoGateway.update(medicacaoMapper.toDomain(dto, idMedicacao));
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{idMedicacao}")
    public ResponseEntity<Void> deleteMedicacao(@PathVariable int idMedicacao) {
        medicacaoGateway.delete(idMedicacao);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Medicacao>> getAllMedicacoes() {
        List<Medicacao> list = medicacaoGateway.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/sigtap/{sigtapMedicacao}")
    public ResponseEntity<Medicacao> getMedicacaoBySigtap(@PathVariable String sigtapMedicacao) {
        Optional<Medicacao> medicacao = medicacaoGateway.findMedicacaoBySigtap(sigtapMedicacao);
        return medicacao.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/nome/{nomeMedicacao}")
    public ResponseEntity<Medicacao> getMedicacaoByNome(@PathVariable String nomeMedicacao) {
        Optional<Medicacao> medicacao = medicacaoGateway.findMedicacaoByNome(nomeMedicacao);
        return medicacao.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}