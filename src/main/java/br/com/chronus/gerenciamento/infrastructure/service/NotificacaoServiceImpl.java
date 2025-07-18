package br.com.chronus.gerenciamento.infrastructure.service;

import br.com.chronus.gerenciamento.application.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificacaoServiceImpl implements NotificacaoService {

    public void enviarNotificacao(Long pacienteId, String mensagem) {
        // Implementar lógica de envio (SMS, email, push notification, etc)
        log.info("Enviando notificação para paciente {}: {}",
                pacienteId, mensagem);
    }
}