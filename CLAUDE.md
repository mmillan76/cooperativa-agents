# cooperativa-agents

Proyecto de aprendizaje: pipeline Java 21 + Spring Boot 4.1 que lee Google Sheets de una cooperativa
ficticia, encadena 3 agentes LLM (Analista, Auditor, Redactor) y genera un informe .pptx.

@docs/arquitectura.md
@docs/estado.md
@docs/decisiones.md
@docs/dataset.md

## Reglas

- La arquitectura de referencia es `docs/arquitectura.md`. No la contradigas sin proponerlo como cambio y registrarlo en `docs/decisiones.md`.
- Antes de proponer trabajo, revisa `docs/estado.md` y continúa desde el hito actual. Un hito cada vez.
- Las cifras las calcula código Java (tools). El LLM nunca inventa ni calcula números.
- `domain` y `pipeline` no dependen de Spring ni de ningún SDK de LLM o Google.
- Entre pasos solo viajan `record`s tipados; nunca texto libre.
- Cada regla y validador lleva tests JUnit que no llaman a ningún LLM.
- Nunca commitear credenciales: ni JSON de service account ni API keys. Todo por variables de entorno.
- Explica el concepto, luego el código, luego cómo probarlo. Señala tradeoffs sin suavizar.
- Al cerrar una sesión: actualiza `docs/estado.md` y añade las decisiones nuevas a `docs/decisiones.md`.

## Comandos

```bash
mvn verify                                   # compila y pasa los tests
mvn -pl app spring-boot:run                  # ejecuta el pipeline
python tools/dataset/generate_dataset.py --out data   # regenera el dataset
```
