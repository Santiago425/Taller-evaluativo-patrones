package configuracion;

import java.util.*;

public final class ConfiguracionEstacion implements Cloneable {
    private String zona;
    private int intervaloMinutos;
    private List<String> contaminantes;
    private Map<String, Double> umbrales;
    private List<TareaMantenimiento> tareas;
    public ConfiguracionEstacion(String zona, int intervaloMinutos, List<String> contaminantes, Map<String, Double> umbrales, List<TareaMantenimiento> tareas) {
        this.zona = zona; this.intervaloMinutos = intervaloMinutos; this.contaminantes = new ArrayList<>(contaminantes); this.umbrales = new HashMap<>(umbrales); this.tareas = new ArrayList<>(tareas);
    }
    public String zona() { return zona; }
    public List<String> contaminantes() { return Collections.unmodifiableList(contaminantes); }
    public Map<String, Double> umbrales() { return Collections.unmodifiableMap(umbrales); }
    public List<TareaMantenimiento> tareas() { return Collections.unmodifiableList(tareas); }
    public void cambiarUmbral(String contaminante, double valor) { umbrales.put(contaminante, valor); }
    public void agregarTarea(TareaMantenimiento tarea) { tareas.add(tarea); }
    public ConfiguracionEstacion clone() { return new ConfiguracionEstacion(zona, intervaloMinutos, new ArrayList<>(contaminantes), new HashMap<>(umbrales), tareas.stream().map(TareaMantenimiento::clone).toList()); }
}
