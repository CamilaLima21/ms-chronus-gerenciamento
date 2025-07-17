package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.Enfermidade;
import br.com.chronus.gerenciamento.application.dto.enfermidades.EnfermidadeRequest;
import br.com.chronus.gerenciamento.application.enums.EnumEnfermidade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnfermidadeMapperTest {

    private final EnfermidadeMapper mapper = new EnfermidadeMapper();

    @Test
    void mapToDomain_shouldMapAllFieldsCorrectly() {
        EnfermidadeRequest dto = new EnfermidadeRequest();
        dto.setEnfermidade(EnumEnfermidade.DIABETES);
        dto.setDescricaoEnfermidade("Descrição exemplo");
        dto.setCid("E11");

        Integer id = 42;

        Enfermidade domain = mapper.mapToDomain(dto, id);

        assertNotNull(domain);
        assertEquals(id, domain.getIdEnfermidade());
        assertEquals(dto.getEnfermidade(), domain.getEnfermidade());
        assertEquals(dto.getDescricaoEnfermidade(), domain.getDescricaoEnfermidade());
        assertEquals(dto.getCid(), domain.getCid());
    }
}
