package io.github.mmillan76.cooperativa.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Hecho calculado por código (nunca por el LLM). Los agentes lo citan por su id, p. ej. [F12].
 *
 * @param id     identificador estable dentro de una ejecución (F1, F2, ...)
 * @param metric nombre legible de la métrica ("margen_bruto_tomate_T3")
 * @param value  valor exacto
 * @param unit   unidad ("EUR", "kg", "%", "kg/ha")
 * @param source de dónde sale el dato
 */
public record Fact(String id, String metric, BigDecimal value, String unit, SourceRef source) {

    public Fact {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(metric, "metric");
        Objects.requireNonNull(value, "value");
        Objects.requireNonNull(unit, "unit");
        Objects.requireNonNull(source, "source");
        if (!id.matches("F\\d+")) {
            throw new IllegalArgumentException("El id de un hecho debe tener la forma F<n>: " + id);
        }
    }
}
