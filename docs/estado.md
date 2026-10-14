# Estado del proyecto

_Actualizar al final de cada sesión de trabajo._

**Hito actual:** H1 · Ingesta y contrato de hojas
**Última actualización:** 2026-10-13

## Hitos

| Hito | Contenido | Estado |
| --- | --- | --- |
| H0 | Esqueleto multimódulo, `Step`/`Pipeline` con checkpoints, records del dominio, dataset, CI | Hecho |
| H1 | Service account, lectura de Sheets (`batchGet`), `sheets-contract.yml`, fallo claro ante A7 | Pendiente |
| H2 | Normalización a registros tipados, `DataQualityReport` (A3, A8) | Pendiente |
| H3 | `LlmGateway` con Spring AI 2.0: Ollama, Anthropic, OpenAI; modelo por paso | Pendiente |
| H4 | Agente Analista con tool calling → `FactTable` con `SourceRef` | Pendiente |
| H5 | Agente Auditor: reglas (A1, A2, A4, A5) + validador de cifras y citas + reintentos; prueba de A6 | Pendiente |
| H6 | Agente Redactor → `DeckSpec`; render `.pptx` con Apache POI; subida a Drive | Pendiente |
| H7 | Observabilidad: OTel + Langfuse, tokens y coste por paso | Pendiente |
| H8 | Evals contra `data/expected/` en job manual de CI; comparativa de modelos | Pendiente |
| H9 | Despliegue: Cloud Run Job + Scheduler + Secret Manager | Pendiente |
| Extra | Ingesta expuesta como servidor MCP (`@McpTool`) | Opcional |

## Hecho en H0

- Proyecto Maven multimódulo: `domain`, `pipeline`, `adapters`, `app` (Spring Boot 4.1.1, Java 21).
- `Pipeline` tipado con reanudación por checkpoints (en memoria) y tests.
- Records `Fact`, `FactTable`, `SourceRef`, `Observation` y catálogo `ObservationType`.
- Generador del dataset con 8 anomalías documentadas en `data/expected/anomalias.json`.
- GitHub Actions con `mvn verify`.

## Próximos pasos (H1)

1. Crear proyecto GCP de pruebas, habilitar Sheets API y Drive API, crear service account.
2. Generar el dataset, subir `data/2026-T3/*.xlsx` a `/cooperativa-demo/entrada/2026-T3/` en Drive (convertir a Sheets) y compartir la carpeta con la service account.
3. Definir `sheets-contract.yml` a partir de `docs/dataset.md`.
4. Implementar el adaptador de Sheets y el validador de contrato, con test que falle ante la variante A7.

## Abierto

- Nada todavía.
