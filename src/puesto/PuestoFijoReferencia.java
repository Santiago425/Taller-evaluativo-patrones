package puesto;

import analizador.*;
import configuracion.ConfiguracionEstacion;
public final class PuestoFijoReferencia extends PuestoDeMonitoreo {
    public PuestoFijoReferencia(String n, ConfiguracionEstacion c) { super(n,c); }
    protected Analizador crearAnalizador(String c) { return new AnalizadorReferencia(1.02); }
    public String tipo() { return "FIJO_REFERENCIA"; }
}
