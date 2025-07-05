package br.com.chronus.gerenciamento.application.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEnfermidadeRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name length must be less than 100 characters")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Name must contain only letters and spaces")
    private String nomeEnfermidade;
    private String cid;
}
