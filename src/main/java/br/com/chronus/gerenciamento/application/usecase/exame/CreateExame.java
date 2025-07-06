package br.com.chronus.gerenciamento.application.usecase.exame;

import br.com.chronus.gerenciamento.application.domain.Exame;
import br.com.chronus.gerenciamento.application.gateway.ExameGateway;
import br.com.chronus.gerenciamento.application.usecase.exame.exception.ExameExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateExame {

    private final ExameGateway gateway;

    public Exame execute(final Exame request) {

        final var exame = gateway.findExameById(request.getIdExame());

        if (exame.isPresent()) {
            throw new ExameExistenteException(request.getIdExame());
        }

        final var buildDomain = Exame.createExame(
                request.getIdPaciente(),
                request.getIdProfissionalSaude(),
                request.getDataExame(),
                request.getListaExames(),
                request.getStatusExame()
        );

        return gateway.save(buildDomain);
    }
}