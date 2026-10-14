package io.github.mmillan76.cooperativa.pipeline;

import java.util.Optional;

/**
 * Guarda la salida de cada paso para poder reanudar una ejecución sin repetir llamadas al LLM.
 * H0: en memoria. Más adelante: GCS, con clave hash(input) + versión de prompt + modelo.
 */
public interface CheckpointStore {

    Optional<Object> load(String runId, String stepName);

    void save(String runId, String stepName, Object output);
}
