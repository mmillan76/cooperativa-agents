package io.github.mmillan76.cooperativa.pipeline;

import java.util.Objects;

/**
 * Contexto de una ejecución. Crecerá con: configuración de modelo por paso, presupuesto de tokens,
 * acceso a la FactTable original, etc.
 */
public record RunContext(String runId, CheckpointStore checkpoints) {

    public RunContext {
        Objects.requireNonNull(runId, "runId");
        Objects.requireNonNull(checkpoints, "checkpoints");
    }
}
