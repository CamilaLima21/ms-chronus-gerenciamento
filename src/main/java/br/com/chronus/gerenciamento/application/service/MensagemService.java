package br.com.chronus.gerenciamento.application.service;

import br.com.chronus.gerenciamento.application.domain.Mensagem;

public interface MensagemService {
    void enviarMensagem(Mensagem mensagem);
}