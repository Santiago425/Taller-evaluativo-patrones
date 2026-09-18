package puesto;

import analizador.*;
import configuracion.ConfiguracionEstacion;
import modelo.LecturaCruda;
import java.util.*;

public abstract class PuestoDeMonitoreo {
    private final String nombre; private final ConfiguracionEstacion configuracion;
    protected PuestoDeMonitoreo(String nombre, ConfiguracionEstacion configuracion) { this.nombre = nombre; this.configuracion = configuracion; }
    public String nombre() { return nombre; }
    public ConfiguracionEstacion configuracion() { return configuracion; }
    protected abstract Analizador crearAnalizador(String contaminante);
    public JornadaProcesada procesarJornada(List<LecturaCruda> lecturas) {
        Map<String,Analizador> analizadores = new HashMap<>();
        for (String c : configuracion.contaminantes()) analizadores.put(c, crearAnalizador(c));
        return JornadaProcesada.procesar(lecturas, analizadores, configuracion.contaminantes());
    }
    public abstract String tipo();
}
