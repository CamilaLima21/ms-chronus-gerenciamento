package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.dto.portal.UpdateConteudoPortalSaudeRequest;
import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("chronus/conteudos")
@RequiredArgsConstructor
public class ConteudoPortalSaudeController {

    private final ConteudoPortalSaudeGateway conteudoGateway;

    @PostMapping
    public ResponseEntity<ConteudoPortalSaude> createConteudo(@Validated @RequestBody UpdateConteudoPortalSaudeRequest dto) {
        ConteudoPortalSaude created = conteudoGateway.save(mapToDomain(dto, null));
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{idConteudo}")
    public ResponseEntity<ConteudoPortalSaude> getConteudoById(@PathVariable Integer idConteudo) {
        Optional<ConteudoPortalSaude> conteudo = conteudoGateway.findById(idConteudo);
        return conteudo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{idConteudo}")
    public ResponseEntity<ConteudoPortalSaude> updateConteudo(@PathVariable Integer idConteudo, @Validated @RequestBody UpdateConteudoPortalSaudeRequest dto) {
        ConteudoPortalSaude updated = conteudoGateway.update(mapToDomain(dto, idConteudo));
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{idConteudo}")
    public ResponseEntity<Void> deleteConteudo(@PathVariable Integer idConteudo) {
        conteudoGateway.delete(idConteudo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ConteudoPortalSaude>> getAllConteudos() {
        List<ConteudoPortalSaude> list = conteudoGateway.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/filtro/{filtro}")
    public ResponseEntity<List<ConteudoPortalSaude>> getConteudosByFiltro(@PathVariable EnumFiltroPortalSaude filtro) {
        List<ConteudoPortalSaude> conteudos = conteudoGateway.findByFiltro(filtro);
        return new ResponseEntity<>(conteudos, HttpStatus.OK);
    }

    private ConteudoPortalSaude mapToDomain(UpdateConteudoPortalSaudeRequest dto, Integer id) {
        return ConteudoPortalSaude.builder()
                .id(id)
                .filtroPortalSaude(dto.getFiltroPortalSaude())
                .conteudos(dto.getConteudos() != null
                        ? dto.getConteudos().stream()
                        .map(child -> mapToDomain(child, null))
                        .collect(Collectors.toList())
                        : null)
                .build();
    }
}
