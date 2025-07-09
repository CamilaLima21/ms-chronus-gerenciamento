package br.com.chronus.gerenciamento.application.usecase.consulta;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.gateway.PacienteGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ConsultaExistenteException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.PacienteNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateConsulta {

    private final ConsultaGateway consultaGateway;
    private final PacienteGateway pacienteGateway;

    public Consulta execute(final Consulta requestConsulta) {


        final var consulta = consultaGateway.getConsultaById(requestConsulta.getIdConsulta());
        if (consulta.isPresent()) {
            throw new ConsultaExistenteException(
                    requestConsulta.getIdConsulta(),
                    requestConsulta.getDataHoraConsulta()
            );
        }


        boolean pacienteExiste = pacienteGateway.verificaPacientePorId(requestConsulta.getIdPaciente());
        if (!pacienteExiste) {
            throw new PacienteNaoEncontradoException(requestConsulta.getIdPaciente());
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
