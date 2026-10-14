# cooperativa-agents

Pipeline de agentes LLM en **Java 21 + Spring Boot 4.1** que convierte las hojas de cálculo de una cooperativa
agrícola (ficticia) en un informe PowerPoint con resumen y observaciones **trazables a su fuente**.

```
Google Sheets → Ingesta → Normalización → Agente Analista → Agente Auditor → Agente Redactor → .pptx
                (código)    (código)        (LLM + tools)     (reglas + LLM)    (LLM)             (Apache POI)
```

Qué demuestra:

- **Pipeline tipado** con contratos `record` entre agentes y reanudación por checkpoints.
- **Cálculo determinista vía tool calling**: el LLM decide qué calcular, Java calcula.
- **Guardrails anti-alucinación**: cada cifra y cada cita se valida contra la tabla de hechos.
- **LLM agnóstico**: Ollama, Anthropic y OpenAI detrás de un puerto propio (Spring AI 2.0).
- **Evals** sobre un dataset generado con 8 anomalías conocidas, incluida una prompt injection en una celda.

> Proyecto en construcción, hito a hito. Estado actual en [`docs/estado.md`](docs/estado.md).

## Documentación

- [Arquitectura](docs/arquitectura.md)
- [Dataset y anomalías](docs/dataset.md)
- [Registro de decisiones](docs/decisiones.md)

## Empezar

```bash
mvn verify
mvn -pl app spring-boot:run

pip install -r tools/dataset/requirements.txt
python tools/dataset/generate_dataset.py --out data
```

Las credenciales (service account de Google, API keys) se pasan siempre por variables de entorno y nunca se versionan.

Todos los datos son ficticios.
