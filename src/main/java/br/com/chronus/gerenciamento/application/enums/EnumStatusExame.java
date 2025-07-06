package br.com.chronus.gerenciamento.application.enums;

public enum EnumStatusExame {
    AGENDADO("Exame agendado"),
    EM_ANDAMENTO("Exame em andamento"),
    CONCLUIDO("Exame concluído"),
    CANCELADO("Exame cancelado"),
    NAO_REALIZADO("Exame não realizado"),
    AGUARDANDO_RESULTADO("Aguardando resultado"),
    RESULTADO_DISPONIVEL("Resultado disponível"),
    REAGENDADO("Exame reagendado");

    private final String descricao;

    EnumStatusExame(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
