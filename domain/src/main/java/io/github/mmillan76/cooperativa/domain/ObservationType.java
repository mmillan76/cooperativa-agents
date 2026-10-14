package io.github.mmillan76.cooperativa.domain;

/**
 * Catálogo cerrado de observaciones. Todas salvo INTERPRETACION las detectan reglas en código;
 * INTERPRETACION es la única libre del LLM y se marca distinto en el informe.
 */
public enum ObservationType {
    DESVIACION_COSTE,
    VARIACION_PRECIO,
    RENDIMIENTO_ANOMALO,
    DATO_FALTANTE,
    AYUDA_PENDIENTE,
    INCOHERENCIA_ENTRE_HOJAS,
    INTERPRETACION
}
