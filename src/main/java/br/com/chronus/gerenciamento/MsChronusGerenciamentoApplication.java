package br.com.chronus.gerenciamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "br.com.chronus.gerenciamento.infrastructure.integration")
public class MsChronusGerenciamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsChronusGerenciamentoApplication.class, args);
	}

}
