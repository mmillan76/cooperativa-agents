package io.github.mmillan76.cooperativa.pipeline;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryCheckpointStore implements CheckpointStore {

    private final Map<String, Object> store = new ConcurrentHashMap<>();

    @Override
    public Optional<Object> load(String runId, String stepName) {
        return Optional.ofNullable(store.get(key(runId, stepName)));
    }

    @Override
    public void save(String runId, String stepName, Object output) {
        store.put(key(runId, stepName), output);
    }

    private static String key(String runId, String stepName) {
        return runId + "/" + stepName;
    }
}
