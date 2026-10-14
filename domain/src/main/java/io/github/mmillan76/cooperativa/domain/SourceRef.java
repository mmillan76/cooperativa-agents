package io.github.mmillan76.cooperativa.domain;

import java.util.Objects;

/**
 * Dónde está el dato en origen: fichero de Drive, hoja y rango A1.
 * Toda cifra y toda observación del informe debe poder rastrearse hasta un SourceRef.
 */
public record SourceRef(String file, String sheet, String range) {

    public SourceRef {
        Objects.requireNonNull(file, "file");
        Objects.requireNonNull(sheet, "sheet");
        Objects.requireNonNull(range, "range");
    }

    @Override
    public String toString() {
        return file + "!" + sheet + "!" + range;
    }
}
