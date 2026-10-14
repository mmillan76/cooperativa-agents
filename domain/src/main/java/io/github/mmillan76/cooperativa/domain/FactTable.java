package io.github.mmillan76.cooperativa.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Conjunto inmutable de hechos de una ejecución. Es la única fuente de cifras del informe:
 * el render pinta valores desde aquí y los validadores comprueban contra ella lo que escribe el LLM.
 */
public final class FactTable {

    private final Map<String, Fact> facts;

    private FactTable(Map<String, Fact> facts) {
        this.facts = Collections.unmodifiableMap(facts);
    }

    public static FactTable of(List<Fact> facts) {
        Map<String, Fact> byId = new LinkedHashMap<>();
        for (Fact fact : facts) {
            if (byId.putIfAbsent(fact.id(), fact) != null) {
                throw new IllegalArgumentException("Id de hecho duplicado: " + fact.id());
            }
        }
        return new FactTable(byId);
    }

    public Optional<Fact> find(String id) {
        return Optional.ofNullable(facts.get(id));
    }

    public boolean contains(String id) {
        return facts.containsKey(id);
    }

    public Collection<Fact> all() {
        return facts.values();
    }

    public int size() {
        return facts.size();
    }
}
