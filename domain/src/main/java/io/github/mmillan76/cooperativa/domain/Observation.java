package io.github.mmillan76.cooperativa.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * Observación del informe. Siempre cita al menos un hecho de la FactTable.
 */
public record Observation(
        @NotBlank String text,
        @NotEmpty List<@NotBlank String> factIds,
        @NotNull Severity severity,
        @NotNull ObservationType type) {

    public Observation {
        factIds = factIds == null ? List.of() : List.copyOf(factIds);
    }
}
