package io.github.mmillan76.cooperativa.app;

import io.github.mmillan76.cooperativa.pipeline.InMemoryCheckpointStore;
import io.github.mmillan76.cooperativa.pipeline.Pipeline;
import io.github.mmillan76.cooperativa.pipeline.RunContext;
import io.github.mmillan76.cooperativa.pipeline.Step;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * H0: pipeline de demostración con pasos triviales, para comprobar el cableado
 * de punta a punta.
 * Se sustituye por el pipeline real a partir de H1.
 */
@Component
class DemoPipelineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DemoPipelineRunner.class);

    @Override
    public void run(String... args) {
        Step<String, String> ingesta = step("ingesta", folder -> "datos de " + folder);
        Step<String, Integer> calculo = step("calculo", String::length);
        Step<Integer, String> informe = step("informe", n -> "informe con " + n + " caracteres de entrada");

        var pipeline = Pipeline.of(ingesta).then(calculo).then(informe);
        var ctx = new RunContext(UUID.randomUUID().toString(), new InMemoryCheckpointStore());

        String result = pipeline.run("/cooperativa-demo/entrada/2026-T3", ctx);
        log.info("Pipeline {} -> {}", pipeline.stepNames(), result);
    }

    private static <I, O> Step<I, O> step(String name, java.util.function.Function<I, O> fn) {
        return new Step<>() {
            @Override
            public String name() {
                return name;
            }

            @Override
            public O execute(I input, RunContext ctx) {
                return fn.apply(input);
            }
        };
    }
}
