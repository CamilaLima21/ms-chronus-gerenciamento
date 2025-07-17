package br.com.chronus.gerenciamento.application.mapper;

import br.com.chronus.gerenciamento.application.domain.CheckUpSaude;
import br.com.chronus.gerenciamento.application.dto.checkup.CheckUpSaudeRequest;
import br.com.chronus.gerenciamento.infrastructure.persistence.entity.CheckUpSaudeEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CheckUpMapperTest {

    private final CheckUpMapper mapper = new CheckUpMapper();

    @Test
    void testToDomain_shouldMapCorrectly() {
        CheckUpSaudeRequest dto = new CheckUpSaudeRequest();
        dto.setIdPaciente(10);
        dto.setIdProfissionalSaude(20);
        dto.setGlicemia("100");
        dto.setPressaoArterial("120/80");
        dto.setFrequenciaCardiaca("70");
        dto.setFrequenciaRespiratoria("16");
        dto.setTemperaturaCorporal("36.6");
        dto.setSaturacaoOxigenio("98%");
        dto.setOutrosDados("Nenhum");
        dto.setObservacoes("Observação teste");

        Integer id = 5;

        CheckUpSaude domain = mapper.toDomain(dto, id);

        assertNotNull(domain);
        assertEquals(id, domain.getIdCheckUpsaude());
        assertEquals(dto.getIdPaciente(), domain.getIdPaciente());
        assertEquals(dto.getIdProfissionalSaude(), domain.getIdProfissionalSaude());
        assertEquals(dto.getGlicemia(), domain.getGlicemia());
        assertEquals(dto.getPressaoArterial(), domain.getPressaoArterial());
        assertEquals(dto.getFrequenciaCardiaca(), domain.getFrequenciaCardiaca());
        assertEquals(dto.getFrequenciaRespiratoria(), domain.getFrequenciaRespiratoria());
        assertEquals(dto.getTemperaturaCorporal(), domain.getTemperaturaCorporal());
        assertEquals(dto.getSaturacaoOxigenio(), domain.getSaturacaoOxigenio());
        assertEquals(dto.getOutrosDados(), domain.getOutrosDados());
        assertEquals(dto.getObservacoes(), domain.getObservacoes());
        assertNotNull(domain.getDataHoraRegistro());
        // A dataHoraRegistro é setada com LocalDateTime.now() dentro do método
        // então só verificamos se não é nulo
    }

    @Test
    void testMapToDomain_shouldMapEntityToDomain() {
        LocalDateTime now = LocalDateTime.now();

        CheckUpSaudeEntity entity = CheckUpSaudeEntity.builder()
                .idCheckUpsaude(15)
                .idPaciente(30)
                .idProfissionalSaude(40)
                .glicemia("90")
                .pressaoArterial("110/70")
                .frequenciaCardiaca("65")
                .frequenciaRespiratoria("18")
                .temperaturaCorporal("37.0")
                .saturacaoOxigenio("97%")
                .outrosDados("Dados adicionais")
                .observacoes("Observações")
                .dataHoraRegistro(now)
                .build();

        CheckUpSaude domain = mapper.mapToDomain(entity);

        assertNotNull(domain);
        assertEquals(entity.getIdCheckUpsaude(), domain.getIdCheckUpsaude());
        assertEquals(entity.getIdPaciente(), domain.getIdPaciente());
        assertEquals(entity.getIdProfissionalSaude(), domain.getIdProfissionalSaude());
        assertEquals(entity.getGlicemia(), domain.getGlicemia());
        assertEquals(entity.getPressaoArterial(), domain.getPressaoArterial());
        assertEquals(entity.getFrequenciaCardiaca(), domain.getFrequenciaCardiaca());
        assertEquals(entity.getFrequenciaRespiratoria(), domain.getFrequenciaRespiratoria());
        assertEquals(entity.getTemperaturaCorporal(), domain.getTemperaturaCorporal());
        assertEquals(entity.getSaturacaoOxigenio(), domain.getSaturacaoOxigenio());
        assertEquals(entity.getOutrosDados(), domain.getOutrosDados());
        assertEquals(entity.getObservacoes(), domain.getObservacoes());
        assertEquals(entity.getDataHoraRegistro(), domain.getDataHoraRegistro());
    }

    @Test
    void testMapToEntity_shouldMapDomainToEntity() {
        LocalDateTime now = LocalDateTime.now();

        CheckUpSaude domain = CheckUpSaude.builder()
                .idCheckUpsaude(25)
                .idPaciente(50)
                .idProfissionalSaude(60)
                .glicemia("85")
                .pressaoArterial("115/75")
                .frequenciaCardiaca("72")
                .frequenciaRespiratoria("19")
                .temperaturaCorporal("36.8")
                .saturacaoOxigenio("99%")
                .outrosDados("Outros dados")
                .observacoes("Algumas observações")
                .dataHoraRegistro(now)
                .build();

        CheckUpSaudeEntity entity = mapper.mapToEntity(domain);

        assertNotNull(entity);
        assertEquals(domain.getIdCheckUpsaude(), entity.getIdCheckUpsaude());
        assertEquals(domain.getIdPaciente(), entity.getIdPaciente());
        assertEquals(domain.getIdProfissionalSaude(), entity.getIdProfissionalSaude());
        assertEquals(domain.getGlicemia(), entity.getGlicemia());
        assertEquals(domain.getPressaoArterial(), entity.getPressaoArterial());
        assertEquals(domain.getFrequenciaCardiaca(), entity.getFrequenciaCardiaca());
        assertEquals(domain.getFrequenciaRespiratoria(), entity.getFrequenciaRespiratoria());
        assertEquals(domain.getTemperaturaCorporal(), entity.getTemperaturaCorporal());
        assertEquals(domain.getSaturacaoOxigenio(), entity.getSaturacaoOxigenio());
        assertEquals(domain.getOutrosDados(), entity.getOutrosDados());
        assertEquals(domain.getObservacoes(), entity.getObservacoes());
        assertEquals(domain.getDataHoraRegistro(), entity.getDataHoraRegistro());
    }
}
