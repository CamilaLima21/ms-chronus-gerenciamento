package br.com.chronus.gerenciamento.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckUpSaudeRequest {

    @NotNull(message = "O ID do paciente é obrigatório")
    private Integer idPaciente;

    //@NotNull(message = "O ID do profissional de saúde é obrigatório")
    private Integer idProfissionalSaude;

    @Size(max = 20, message = "A glicemia deve ter no máximo 20 caracteres")
    private String glicemia;

    @Size(max = 20, message = "A pressão arterial deve ter no máximo 20 caracteres")
    private String pressaoArterial;

    @Size(max = 20, message = "A frequência cardíaca deve ter no máximo 20 caracteres")
    private String frequenciaCardiaca;

    @Size(max = 20, message = "A frequência respiratória deve ter no máximo 20 caracteres")
    private String frequenciaRespiratoria;

    @Size(max = 20, message = "A temperatura corporal deve ter no máximo 20 caracteres")
    private String temperaturaCorporal;

    @Size(max = 20, message = "A saturação de oxigênio deve ter no máximo 20 caracteres")
    private String saturacaoOxigenio;

    @Size(max = 255, message = "Outros dados devem ter no máximo 255 caracteres")
    private String outrosDados;

    @Size(max = 500, message = "As observações devem ter no máximo 500 caracteres")
    private String observacoes;
}
