package io.github.mmillan76.cooperativa.pipeline;

/**
 * Un paso del pipeline (determinista o agente LLM). Entrada y salida son tipos del dominio,
 * nunca texto libre: así cada frontera entre agentes es un contrato validable.
 */
public interface Step<I, O> {

    /** Nombre estable: se usa en trazas, métricas y como clave de checkpoint. */
    String name();

    O execute(I input, RunContext ctx);
}
