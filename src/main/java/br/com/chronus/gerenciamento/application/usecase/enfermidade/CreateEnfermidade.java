package br.com.chronus.gerenciamento.application.usecase.enfermidade;


import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.gateway.EnfermidadeGateway;
import br.com.chronus.gerenciamento.application.usecase.enfermidade.exception.EnfermidadeExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateEnfermidade {

    private final EnfermidadeGateway gateway;

    public Enfermidade execute(final Enfermidade request) {

        final var enfermidade = gateway.findEnfermidadeById(request.getIdEnfermidade());

        if (enfermidade.isPresent()) {
            throw new EnfermidadeExistenteException(request.getIdEnfermidade(), request.getNomeEnfermidade());
        }

        final var buildDomain =
                Enfermidade.createEnfermidade(
                        request.getNomeEnfermidade(),
                        request.getCid());

        return gateway.save(buildDomain);
    }
}
