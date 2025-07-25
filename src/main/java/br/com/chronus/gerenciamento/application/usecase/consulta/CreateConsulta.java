package br.com.chronus.gerenciamento.application.usecase.consulta;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ConsultaExistenteException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateConsulta {

    private final ConsultaGateway consultaGateway;
    private final PacienteGateway pacienteGateway;
    private final ProfissionalSaudeGateway profissionalSaudeGateway;

    public Consulta execute(final Consulta requestConsulta) {

        if (requestConsulta.getIdConsulta() != null) {
            final var consulta = consultaGateway.getConsultaById(requestConsulta.getIdConsulta());
            if (consulta.isPresent()) {
                throw new ConsultaExistenteException(
                        requestConsulta.getIdConsulta(),
                        requestConsulta.getDataHoraConsulta()
                );
            }
        }

        boolean pacienteExiste = pacienteGateway.verificaPacientePorId(requestConsulta.getIdPaciente());
        if (!pacienteExiste) {
            throw new PacienteNaoEncontradoException(requestConsulta.getIdPaciente());
        }

        boolean profissionalExiste = profissionalSaudeGateway.verificaProfissionalPorId(requestConsulta.getIdProfissionalSaude());
        if (!profissionalExiste) {
            throw new ProfissionalSaudeNaoEncontradoException(requestConsulta.getIdProfissionalSaude());
        }

        final var buildDomain = Consulta.createConsulta(
                requestConsulta.getIdPaciente(),
                requestConsulta.getIdProfissionalSaude(),
                requestConsulta.getDataHoraConsulta(),
                requestConsulta.getObservacaoConsulta(),
                requestConsulta.getStatusConsulta(),
                requestConsulta.getTipoConsulta(),
                requestConsulta.getMotivoCancelamento()
        );

        return consultaGateway.createConsulta(buildDomain);
    }
}