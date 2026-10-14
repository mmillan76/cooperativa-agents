package io.github.mmillan76.cooperativa.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class FactTableTest {

    private static final SourceRef SRC = new SourceRef("Ventas", "Ventas", "D2:D7");

    @Test
    void encuentraHechosPorId() {
        var table = FactTable.of(List.of(
                new Fact("F1", "precio_medio_tomate_T3", new BigDecimal("0.07"), "EUR/kg", SRC),
                new Fact("F2", "variacion_precio_tomate", new BigDecimal("-22.0"), "%", SRC)));

        assertThat(table.size()).isEqualTo(2);
        assertThat(table.contains("F2")).isTrue();
        assertThat(table.find("F1")).get().extracting(Fact::unit).isEqualTo("EUR/kg");
        assertThat(table.find("F99")).isEmpty();
    }

    @Test
    void rechazaIdsDuplicados() {
        var f = new Fact("F1", "x", BigDecimal.ONE, "EUR", SRC);
        assertThatThrownBy(() -> FactTable.of(List.of(f, f)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("F1");
    }

    @Test
    void rechazaIdsMalFormados() {
        assertThatThrownBy(() -> new Fact("hecho-1", "x", BigDecimal.ONE, "EUR", SRC))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
