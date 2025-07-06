package br.com.chronus.gerenciamento.infrastructure.service;

import br.com.chronus.gerenciamento.application.domain.Mensagem;
import br.com.chronus.gerenciamento.application.service.MensagemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MensagemServiceImpl implements MensagemService {

    @Value("${mensageria.url}")
    private String mensageriaUrl;

    private final RestTemplate restTemplate;

    public MensagemServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void enviarMensagem(Mensagem mensagem) {
        restTemplate.postForEntity(mensageriaUrl, mensagem, Void.class);
    }
}