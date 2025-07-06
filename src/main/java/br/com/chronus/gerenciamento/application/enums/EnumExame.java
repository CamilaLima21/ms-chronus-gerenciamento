package br.com.chronus.gerenciamento.application.enums;

public enum EnumExame {
    // ASMA
    ESPIROMETRIA_ASMA(EnumEnfermidade.ASMA),
    GASOMETRIA_ARTERIAL_ASMA(EnumEnfermidade.ASMA),

    // CÂNCERES
    PAPANICOLAU_CANCERES(EnumEnfermidade.CANCERES),
    MAMOGRAFIA_CANCERES(EnumEnfermidade.CANCERES),
    PSA_CANCERES(EnumEnfermidade.CANCERES),

    // COLESTEROL ALTO
    PERFIL_LIPIDICO_COLESTEROL(EnumEnfermidade.COLESTEROL_ALTO),

    // DIABETES
    GLICEMIA_JEJUM_DIABETES(EnumEnfermidade.DIABETES),
    HEMOGLOBINA_GLICADA_DIABETES(EnumEnfermidade.DIABETES),
    MICROALBUMINURIA_DIABETES(EnumEnfermidade.DIABETES),

    // DOENÇA CARDIOVASCULAR
    ELETROCARDIOGRAMA_CARDIO(EnumEnfermidade.DOENCA_CARDIO_VASCULAR),
    ECOCARDIOGRAMA_CARDIO(EnumEnfermidade.DOENCA_CARDIO_VASCULAR),

    // DOENÇA HEPÁTICA CRÔNICA
    TGO_TGP_HEPATICA(EnumEnfermidade.DOENCA_HEPATICA_CRONICA),
    ULTRASSONOGRAFIA_ABDOMINAL_HEPATICA(EnumEnfermidade.DOENCA_HEPATICA_CRONICA),

    // DPOC
    ESPIROMETRIA_DPOC(EnumEnfermidade.DOENCA_PULMONAR_OBSTRUTIVA_CRONICA),

    // DOENÇA RENAL CRÔNICA
    CREATININA_RENAL(EnumEnfermidade.DOENCA_RENAL_CRONICA),
    UREIA_RENAL(EnumEnfermidade.DOENCA_RENAL_CRONICA),

    // DOENÇA REUMÁTICA
    FATOR_REUMATOIDE_REUMATICA(EnumEnfermidade.DOENCA_REUMATICA),

    // HIV/AIDS
    TESTE_RAPIDO_HIV(EnumEnfermidade.HIV_AIDS),

    // HIPERTENSÃO ARTERIAL
    MAPA_HIPERTENSAO(EnumEnfermidade.HIPERTENSAO_ARTERIAL),

    // OBESIDADE
    IMC_OBESIDADE(EnumEnfermidade.OBESIDADE);

    private final EnumEnfermidade enfermidade;

    EnumExame(EnumEnfermidade enfermidade) {
        this.enfermidade = enfermidade;
    }

    public EnumEnfermidade getEnfermidade() {
        return enfermidade;
    }
}