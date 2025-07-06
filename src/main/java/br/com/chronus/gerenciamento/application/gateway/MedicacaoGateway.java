package br.com.chronus.gerenciamento.application.gateway;

import br.com.chronus.gerenciamento.application.domain.Medicacao;

import java.util.List;
import java.util.Optional;

public interface MedicacaoGateway {

    Medicacao save(final Medicacao medicacao);

    Optional<Medicacao> findMedicacaoById(final int idMedicacao);

    Medicacao update(final Medicacao medicacao);

    void delete(final Integer idMedicacao);

    List<Medicacao> findAll();

    Optional<Medicacao> findMedicacaoBySigtap(final String sigtapMedicacao);

    Optional<Medicacao> findMedicacaoByNome(final String nomeMedicacao);
}