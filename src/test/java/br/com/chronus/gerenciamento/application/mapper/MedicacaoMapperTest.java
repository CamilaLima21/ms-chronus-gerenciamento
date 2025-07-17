package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Medicacao;
import br.com.chronus.gerenciamento.application.dto.medicacao.UpdateMedicacaoRequest;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.MedicacaoEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicacaoMapperTest {

    private final MedicacaoMapper mapper = new MedicacaoMapper();

    @Test
    void toDomain_shouldMapDtoToDomain() {
        UpdateMedicacaoRequest dto = new UpdateMedicacaoRequest();
        dto.setNomeMedicacao("Dipirona");
        dto.setDescricaoMedicacao("Analgésico e antipirético");
        dto.setSigtapMedicacao("12345");

        Medicacao domain = mapper.toDomain(dto, 10);

        assertNotNull(domain);
        assertEquals(10, domain.getIdMedicacao());
        assertEquals(dto.getNomeMedicacao(), domain.getNomeMedicacao());
        assertEquals(dto.getDescricaoMedicacao(), domain.getDescricaoMedicacao());
        assertEquals(dto.getSigtapMedicacao(), domain.getSigtapMedicacao());
    }

    @Test
    void mapMedicamentoToEntity_shouldMapDomainToEntity() {
        Medicacao domain = Medicacao.builder()
                .idMedicacao(20)
                .nomeMedicacao("Paracetamol")
                .descricaoMedicacao("Analgésico")
                .sigtapMedicacao("67890")
                .build();

        MedicacaoEntity entity = mapper.mapMedicamentoToEntity(domain);

        assertNotNull(entity);
        assertEquals(domain.getIdMedicacao(), entity.getIdMedicacao());
        assertEquals(domain.getNomeMedicacao(), entity.getNomeMedicacao());
        assertEquals(domain.getDescricaoMedicacao(), entity.getDescricaoMedicacao());
        assertEquals(domain.getSigtapMedicacao(), entity.getSigtapMedicacao());
    }

    @Test
    void mapMedicamentoToEntity_shouldReturnNullIfDomainIsNull() {
        assertNull(mapper.mapMedicamentoToEntity(null));
    }

    @Test
    void mapEntityToMedicamento_shouldMapEntityToDomain() {
        MedicacaoEntity entity = MedicacaoEntity.builder()
                .idMedicacao(30)
                .nomeMedicacao("Ibuprofeno")
                .descricaoMedicacao("Anti-inflamatório")
                .sigtapMedicacao("54321")
                .build();

        Medicacao domain = mapper.mapEntityToMedicamento(entity);

        assertNotNull(domain);
        assertEquals(entity.getIdMedicacao(), domain.getIdMedicacao());
        assertEquals(entity.getNomeMedicacao(), domain.getNomeMedicacao());
        assertEquals(entity.getDescricaoMedicacao(), domain.getDescricaoMedicacao());
        assertEquals(entity.getSigtapMedicacao(), domain.getSigtapMedicacao());
    }

    @Test
    void mapEntityToMedicamento_shouldReturnNullIfEntityIsNull() {
        assertNull(mapper.mapEntityToMedicamento(null));
    }
}
