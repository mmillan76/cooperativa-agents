package io.github.mmillan76.cooperativa.pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Pipeline secuencial y tipado: la salida de un paso es la entrada del siguiente, y el compilador
 * comprueba que los tipos encajan.
 *
 * <pre>{@code
 * Pipeline<DriveFolder, DeckSpec> p = Pipeline.of(ingesta)
 *         .then(calculo)
 *         .then(analista)
 *         .then(auditor)
 *         .then(redactor);
 * }</pre>
 */
public final class Pipeline<I, O> {

    private final List<Step<?, ?>> steps;

    private Pipeline(List<Step<?, ?>> steps) {
        this.steps = List.copyOf(steps);
    }

    public static <I, O> Pipeline<I, O> of(Step<I, O> first) {
        return new Pipeline<>(List.of(first));
    }

    public <R> Pipeline<I, R> then(Step<? super O, R> next) {
        List<Step<?, ?>> extended = new ArrayList<>(steps);
        extended.add(next);
        return new Pipeline<>(extended);
    }

    public List<String> stepNames() {
        return steps.stream().map(Step::name).toList();
    }

    /**
     * Ejecuta los pasos en orden. Si un paso ya tiene checkpoint para este runId, reutiliza su salida
     * (reanudar tras un fallo sin volver a pagar los pasos anteriores).
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public O run(I input, RunContext ctx) {
        Object current = input;
        for (Step step : steps) {
            Optional<Object> cached = ctx.checkpoints().load(ctx.runId(), step.name());
            if (cached.isPresent()) {
                current = cached.get();
                continue;
            }
            current = step.execute(current, ctx);
            ctx.checkpoints().save(ctx.runId(), step.name(), current);
        }
        return (O) current;
    }
}
