package io.github.mmillan76.cooperativa.pipeline;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class PipelineTest {

    /** Paso de prueba que cuenta cuántas veces se ejecuta. */
    private record Counting<I, O>(String name, java.util.function.Function<I, O> fn, AtomicInteger calls)
            implements Step<I, O> {
        Counting(String name, java.util.function.Function<I, O> fn) {
            this(name, fn, new AtomicInteger());
        }

        @Override
        public O execute(I input, RunContext ctx) {
            calls.incrementAndGet();
            return fn.apply(input);
        }
    }

    @Test
    void encadenaLaSalidaDeUnPasoComoEntradaDelSiguiente() {
        var parse = new Counting<String, Integer>("parse", Integer::parseInt);
        var doble = new Counting<Integer, Integer>("doble", n -> n * 2);
        var texto = new Counting<Integer, String>("texto", n -> "total=" + n);

        Pipeline<String, String> pipeline = Pipeline.of(parse).then(doble).then(texto);

        String out = pipeline.run("21", new RunContext("run-1", new InMemoryCheckpointStore()));

        assertThat(out).isEqualTo("total=42");
        assertThat(pipeline.stepNames()).containsExactly("parse", "doble", "texto");
    }

    @Test
    void reanudaDesdeCheckpointsSinRepetirPasosCompletados() {
        var checkpoints = new InMemoryCheckpointStore();
        var caro = new Counting<Integer, Integer>("agente-caro", n -> n + 1);
        var fallos = new AtomicInteger(1);
        var fragil = new Counting<Integer, Integer>("paso-fragil", n -> {
            if (fallos.getAndDecrement() > 0) {
                throw new IllegalStateException("fallo transitorio");
            }
            return n * 10;
        });
        var pipeline = Pipeline.of(caro).then(fragil);
        var ctx = new RunContext("run-2", checkpoints);

        assertThatThrownBy(() -> pipeline.run(1, ctx)).isInstanceOf(IllegalStateException.class);
        Integer out = pipeline.run(1, ctx);

        assertThat(out).isEqualTo(20);
        assertThat(caro.calls()).hasValue(1);
        assertThat(fragil.calls()).hasValue(2);
    }
}
