# Patrones utilizados

## Factory Method
`puesto.PuestoDeMonitoreo` define `procesarJornada` y declara `crearAnalizador`. Las clases `PuestoFijoReferencia`, `PuestoMovil` y `PuestoBajoCosto` crean el analizador correspondiente. La corrección de lecturas está encapsulada en cada clase de `analizador`, no en condicionales del puesto.

## Prototype
`configuracion.ConfiguracionEstacion` implementa `Cloneable` y redefine `clone()` para copiar profundamente contaminantes, umbrales y tareas. `TareaMantenimiento` también se clona. La demostración modifica una estación clonada sin afectar el modelo ni otra clonación.

## Builder
`boletin.BoletinDiario` tiene estado final, no expone colecciones mutables y se construye mediante `BoletinDiario.Builder`. `build()` valida obligatorios, estaciones no vacías y recomendaciones cuando la categoría es dañina a la salud o peor.
