package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.dto.portal.ConteudoPortalSaudeRequest;
import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import br.com.chronus.gerenciamento.application.mapper.ConteudoPortalSaudeMapper;
import br.com.chronus.gerenciamento.application.usecase.conteudo.CreateConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.usecase.conteudo.DeleteConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.usecase.conteudo.GetAllConteudosPortalSaude;
import br.com.chronus.gerenciamento.application.usecase.conteudo.GetConteudoPortalSaudeByFiltro;
import br.com.chronus.gerenciamento.application.usecase.conteudo.GetConteudoPortalSaudeById;
import br.com.chronus.gerenciamento.application.usecase.conteudo.UpdateConteudoPortalSaude;
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

import java.util.List;

@RestController
@RequestMapping("chronus/conteudos")
@RequiredArgsConstructor
public class ConteudoPortalSaudeController {

    private final ConteudoPortalSaudeGateway conteudoGateway;
    private final CreateConteudoPortalSaude createConteudoPortalSaudeUseCase;
    private final DeleteConteudoPortalSaude deleteConteudoPortalSaudeUseCase;
    private final GetAllConteudosPortalSaude getAllConteudosPortalSaudeUseCase;
    private final GetConteudoPortalSaudeById getConteudoPortalSaudeByIdUseCase;
    private final GetConteudoPortalSaudeByFiltro getConteudoPortalSaudeByFiltroUseCase;
    private final UpdateConteudoPortalSaude updateConteudoPortalSaudeUseCase;
    private final ConteudoPortalSaudeMapper mapper;

    @PostMapping
    public ResponseEntity<ConteudoPortalSaude> createConteudo(@Validated @RequestBody ConteudoPortalSaudeRequest dto) {
        ConteudoPortalSaude created = createConteudoPortalSaudeUseCase.execute(mapper.toDomain(dto, null));
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{idConteudo}")
    public ResponseEntity<ConteudoPortalSaude> getConteudoById(@PathVariable Integer idConteudo) {
        ConteudoPortalSaude conteudo = getConteudoPortalSaudeByIdUseCase.execute(idConteudo);
        return ResponseEntity.ok(conteudo);
    }

    @PutMapping("/{idConteudo}")
    public ResponseEntity<ConteudoPortalSaude> updateConteudo(@PathVariable Integer idConteudo, @Validated @RequestBody ConteudoPortalSaudeRequest dto) {
        ConteudoPortalSaude updated = updateConteudoPortalSaudeUseCase.execute(mapper.toDomain(dto, idConteudo));
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{idConteudo}")
    public ResponseEntity<Void> deleteConteudo(@PathVariable Integer idConteudo) {
        deleteConteudoPortalSaudeUseCase.execute(idConteudo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ConteudoPortalSaude>> getAllConteudos() {
        List<ConteudoPortalSaude> list = getAllConteudosPortalSaudeUseCase.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/filtro/{filtro}")
    public ResponseEntity<List<ConteudoPortalSaude>> getConteudosByFiltro(@PathVariable EnumFiltroPortalSaude filtro) {
        List<ConteudoPortalSaude> conteudos = getConteudoPortalSaudeByFiltroUseCase.execute(filtro);
        return new ResponseEntity<>(conteudos, HttpStatus.OK);
    }

}
