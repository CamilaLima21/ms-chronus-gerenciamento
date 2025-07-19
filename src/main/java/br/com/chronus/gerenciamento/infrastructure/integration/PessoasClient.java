package br.com.chronus.gerenciamento.infrastructure.integration;

import br.com.chronus.gerenciamento.application.domain.ContatoAnjo;
import br.com.chronus.gerenciamento.application.domain.Paciente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-chronus-pessoas", url = "${ms.pessoas.url}")
public interface PessoasClient {

    @GetMapping("/chronus/pacientes/{idPaciente}")
    void getPacienteById(@PathVariable("idPaciente") Integer idPaciente);

    @GetMapping("/chronus/profissionais-saude/{idProfissional}")
    void getProfissionalById(@PathVariable("idProfissional") Integer idProfissional);

    @GetMapping("/chronus/contatos-anjo/paciente/{idPaciente}")
    List<ContatoAnjo> getContatoAnjoPorPaciente(@PathVariable("idPaciente") Integer idPaciente);

    @GetMapping("/chronus/pacientes/{idPaciente}")
    Paciente getPacientePorId(@PathVariable("idPaciente") Integer idPaciente);
}
