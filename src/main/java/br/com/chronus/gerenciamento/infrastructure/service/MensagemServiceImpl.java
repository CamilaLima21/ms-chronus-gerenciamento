package br.com.chronus.gerenciamento.infrastructure.service;

import br.com.chronus.gerenciamento.application.domain.Mensagem;
import br.com.chronus.gerenciamento.application.dto.mensageria.EmailRequest;
import br.com.chronus.gerenciamento.application.dto.mensageria.TelefoneRequest;
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
        String endpoint = mensageriaUrl + "/chronus/mensageria/" + mensagem.getTipo().toLowerCase();

        Object payload = criarPayload(mensagem);
        restTemplate.postForEntity(endpoint, payload, Void.class);
    }

    private Object criarPayload(Mensagem mensagem) {
        return switch (mensagem.getTipo().toLowerCase()) {
            case "email" -> new EmailRequest(
                    mensagem.getDestinatario(),
                    mensagem.getAssunto(),
                    mensagem.getConteudo()
            );
            case "sms", "whatsapp" -> new TelefoneRequest(
                    mensagem.getNumero(),
                    mensagem.getConteudo()
            );
            default -> throw new IllegalArgumentException("Tipo de mensagem inválido: " + mensagem.getTipo());
        };
    }
}