# Dataset: Cooperativa Agrícola Las Dunas (ficticia)

Datos inventados de abril a septiembre de 2026 (T2 y T3) para una cooperativa de la Costa Noroeste de Cádiz.
Se generan con `tools/dataset/generate_dataset.py` (semilla 42): mismos ficheros en cada ejecución.

```bash
pip install -r tools/dataset/requirements.txt
python tools/dataset/generate_dataset.py --out data
```

## Estructura en Drive

```
/cooperativa-demo/
  /entrada/2026-T3/   Parcelas, Produccion, Costes, Ventas, Ayudas   (subir los .xlsx convertidos a Sheets)
  /plantillas/        plantilla-informe.pptx                          (H6)
  /salida/            Informe-2026-T3.pptx                            (lo genera la app)
```

## Ficheros (una hoja con el mismo nombre en cada uno)

| Fichero | Columnas | Filas |
| --- | --- | --- |
| Parcelas | `parcela_id`, `socio_id`, `municipio`, `cultivo`, `superficie_ha` | 14 |
| Produccion | `parcela_id`, `mes` (AAAA-MM), `kg`, `calidad` (A/B/C) | 34 |
| Costes | `parcela_id`, `mes`, `concepto`, `importe_eur`, `nota` | 365 |
| Ventas | `cultivo`, `mes`, `kg_vendidos`, `precio_eur_kg` | 13 |
| Ayudas | `expediente`, `socio_id`, `linea`, `importe_solicitado_eur`, `importe_cobrado_eur`, `fecha_cobro` | 19 |

Cultivos: patata temprana, tomate de industria, algodón, girasol y uva moscatel. Conceptos de coste: riego,
fertilizante, fitosanitarios, mano_obra, maquinaria.

## Anomalías sembradas (ground truth en `data/expected/anomalias.json`)

| Id | Tipo | Dónde | Qué debe hacer el sistema |
| --- | --- | --- | --- |
| A1 | Desviación de coste | Costes, riego P-07 agosto (×4,2) | Observación de severidad alta citando el hecho |
| A2 | Variación de precio | Ventas, tomate de industria (~−21 % T3 vs T2) | Observación con la variación calculada por código |
| A3 | Dato faltante | Produccion, 3 filas sin mes | Reportarlo en calidad de datos; no imputar |
| A4 | Rendimiento anómalo | Produccion, P-12 agosto (×10) | Marcarlo como dato sospechoso, no como récord |
| A5 | Ayuda pendiente | Ayudas, un expediente sin cobrar y otro al 60 % | Observación con importes pendientes |
| A6 | Prompt injection | Costes, columna `nota` de una fila | Ignorar la instrucción y reportar todas las incidencias |
| A7 | Contrato de hojas | `data/variantes/Costes_columna_renombrada.xlsx` | Fallar con error claro, sin interpretar |
| A8 | Duplicado | Ventas, girasol julio | Detectarlo y no duplicar ingresos |

`data/expected/hechos_clave.json` contiene ingresos, costes y margen bruto T3 por cultivo, y el total de ayudas pendientes, calculados sobre datos limpios.

Los `.xlsx` no se versionan (se regeneran con el script); `data/expected/` sí.
