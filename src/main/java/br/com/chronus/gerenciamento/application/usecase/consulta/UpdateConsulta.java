package br.com.chronus.gerenciamento.application.usecase.consulta;

import br.com.chronus.gerenciamento.application.domain.Consulta;
import br.com.chronus.gerenciamento.application.dto.consulta.UpdateConsultaRequest;
import br.com.chronus.gerenciamento.application.gateway.ConsultaGateway;
import br.com.chronus.gerenciamento.application.gateway.ProfissionalSaudeGateway;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ConsultaNaoEncontradaException;
import br.com.chronus.gerenciamento.application.usecase.consulta.exception.ProfissionalSaudeNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateConsulta {

    private final ConsultaGateway consultaGateway;
    private final ProfissionalSaudeGateway profissionalSaudeGateway;

    public Consulta execute(final Integer idConsulta, final UpdateConsultaRequest updateConsultaRequest) {

        final var consultaEncontrada = consultaGateway.getConsultaById(idConsulta)
                .orElseThrow(() -> new ConsultaNaoEncontradaException(idConsulta));

        // Validação condicional do profissional de saúde
        if (updateConsultaRequest.getIdProfissionalSaude() != null) {
            boolean profissionalExiste = profissionalSaudeGateway.verificaProfissionalPorId(
                    updateConsultaRequest.getIdProfissionalSaude());
            if (!profissionalExiste) {
                throw new ProfissionalSaudeNaoEncontradoException(updateConsultaRequest.getIdProfissionalSaude());
            }
        }

        consultaEncontrada.setIdProfissionalSaude(updateConsultaRequest.getIdProfissionalSaude());
        consultaEncontrada.setDataHoraConsulta(updateConsultaRequest.getDataHoraConsulta());
        consultaEncontrada.setStatusConsulta(updateConsultaRequest.getStatusConsulta());
        consultaEncontrada.setTipoConsulta(updateConsultaRequest.getTipoConsulta());

        return consultaGateway.updateConsulta(consultaEncontrada);
    }
}
