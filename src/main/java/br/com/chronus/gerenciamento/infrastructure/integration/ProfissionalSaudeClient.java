package br.com.chronus.gerenciamento.infrastructure.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-chronus-pessoas", url = "${ms.pessoas.url}")
public interface ProfissionalSaudeClient {
    @GetMapping("/id/{idProfissionalSaude}")
    void getProfissionalById(@PathVariable("idProfissionalSaude") Integer idProfissionalSaude);
}
