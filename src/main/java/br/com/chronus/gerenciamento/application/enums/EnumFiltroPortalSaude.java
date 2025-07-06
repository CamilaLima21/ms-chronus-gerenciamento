package br.com.chronus.gerenciamento.application.enums;

public enum EnumFiltroPortalSaude {

    // ASMA
    CUIDADOS_ASMA(EnumEnfermidade.ASMA),
    EXERCICIOS_RESPIRATORIOS_ASMA(EnumEnfermidade.ASMA),

    // CÂNCERES
    PREVENCAO_CANCERES(EnumEnfermidade.CANCERES),
    ORIENTACOES_PACIENTES_CANCERES(EnumEnfermidade.CANCERES),

    // COLESTEROL ALTO
    ALIMENTACAO_COLESTEROL(EnumEnfermidade.COLESTEROL_ALTO),
    ATIVIDADE_FISICA_COLESTEROL(EnumEnfermidade.COLESTEROL_ALTO),

    // DIABETES
    MONITORAMENTO_GLICEMICO_DIABETES(EnumEnfermidade.DIABETES),
    ORIENTACOES_DIETA_DIABETES(EnumEnfermidade.DIABETES),

    // DOENÇA CARDIOVASCULAR
    PREVENCAO_CARDIOVASCULAR(EnumEnfermidade.DOENCA_CARDIO_VASCULAR),
    MEDICACAO_CARDIOVASCULAR(EnumEnfermidade.DOENCA_CARDIO_VASCULAR),

    // DOENÇA HEPÁTICA CRÔNICA
    CUIDADOS_FICADO_HEPATICA(EnumEnfermidade.DOENCA_HEPATICA_CRONICA),

    // DPOC
    TRATAMENTO_DPOC(EnumEnfermidade.DOENCA_PULMONAR_OBSTRUTIVA_CRONICA),

    // DOENÇA RENAL CRÔNICA
    HIDRATACAO_RENAL(EnumEnfermidade.DOENCA_RENAL_CRONICA),

    // DOENÇA REUMÁTICA
    TRATAMENTO_REUMATICO(EnumEnfermidade.DOENCA_REUMATICA),

    // HIV/AIDS
    PREVENCAO_HIV(EnumEnfermidade.HIV_AIDS),
    CUIDADOS_CONTINUADOS_HIV(EnumEnfermidade.HIV_AIDS),

    // HIPERTENSÃO ARTERIAL
    MONITORAMENTO_PRESSAO_HIPERTENSAO(EnumEnfermidade.HIPERTENSAO_ARTERIAL),

    // OBESIDADE
    PLANO_ALIMENTAR_OBESIDADE(EnumEnfermidade.OBESIDADE),
    ATIVIDADE_FISICA_OBESIDADE(EnumEnfermidade.OBESIDADE),

    // OUTROS
    EDUCACAO_EM_SAUDE(EnumEnfermidade.OUTROS);

    private final EnumEnfermidade enfermidade;

    EnumFiltroPortalSaude(EnumEnfermidade enfermidade) {
        this.enfermidade = enfermidade;
    }

    public EnumEnfermidade getEnfermidade() {
        return enfermidade;
    }
}
