package br.com.chronus.gerenciamento.application.service;

public interface NotificacaoService {
    void enviarNotificacao(Long pacienteId, String mensagem);
}