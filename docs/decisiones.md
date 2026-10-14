# Registro de decisiones

Formato: fecha · decisión · alternativas · motivo. Las entradas no se borran; si una decisión cambia, se añade otra que la reemplaza.

## 2026-10-13 · Pipeline secuencial, no multiagente autónomo
- **Alternativas:** orquestación autónoma (un agente planificador que decide qué agente llamar).
- **Motivo:** el flujo es fijo. Un pipeline es más barato, determinista, depurable y evaluable.

## 2026-10-13 · Las cifras las calcula código Java; el LLM las pide mediante tools
- **Alternativas:** que el LLM calcule sobre las hojas.
- **Motivo:** los LLM fallan en aritmética sobre tablas y es la principal fuente de alucinaciones. Las tools hacen el cálculo trazable y testeable.

## 2026-10-13 · Spring Boot 4.1 + Spring AI 2.0
- **Alternativas:** Spring Boot 3.5 (stack actual del trabajo); LangChain4j.
- **Motivo:** el soporte OSS de Boot 3.5 terminó en junio de 2026 y Spring AI 2.0 (GA junio 2026) está diseñado para Boot 4.0/4.1. LangChain4j queda como alternativa detrás de `LlmGateway`.

## 2026-10-13 · Ingesta con APIs de Google, no con MCP
- **Alternativas:** servidor MCP de Drive como fuente.
- **Motivo:** la estructura de las hojas es conocida; la lectura debe ser determinista. MCP queda como extra para exponer la ingesta.

## 2026-10-13 · Service account en lugar de OAuth de usuario
- **Motivo:** evita pantallas de consentimiento y verificación de la app; basta con compartir la carpeta.

## 2026-10-13 · Salida en .pptx con Apache POI
- **Alternativas:** Google Slides API.
- **Motivo:** PowerPoint es el formato pedido para el caso de prueba. Slides queda como segundo renderer posible detrás del mismo `DeckSpec`.

## 2026-10-13 · Dataset ficticio generado con semilla fija
- **Motivo:** repo público sin datos reales y un set dorado con respuestas conocidas para las evals.

## 2026-10-13 · Generador del dataset en Python
- **Alternativas:** generador en Java.
- **Motivo:** es tooling, no parte del producto; Python con openpyxl es lo más directo. El código del producto sigue siendo Java.
