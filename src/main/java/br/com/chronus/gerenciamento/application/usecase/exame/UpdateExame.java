package br.com.chronus.gerenciamento.application.usecase.exame;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.dto.exame.UpdateExameRequest;
import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import br.com.chronus.gerenciamento.application.usecase.exame.exception.ExameNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateExame {

    private final ExameGateway gateway;

    public Exame execute(final int idExame, final UpdateExameRequest request) {
        final var exameFound = gateway.findExameById(idExame)
                .orElseThrow(() -> new ExameNaoEncontradoException(idExame));

        exameFound.setIdPaciente(request.getIdPaciente());
        exameFound.setIdProfissionalSaude(request.getIdProfissionalSaude());
        exameFound.setDataExame(request.getDataExame());
        exameFound.setListaExames(request.getListaExames());
        exameFound.setStatusExame(request.getStatusExame());

        return gateway.update(exameFound);
    }
}