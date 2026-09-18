package puesto;

import analizador.*;
import configuracion.ConfiguracionEstacion;
public final class PuestoBajoCosto extends PuestoDeMonitoreo {
    public PuestoBajoCosto(String n, ConfiguracionEstacion c) { super(n,c); }
    protected Analizador crearAnalizador(String c) { return new AnalizadorBajoCosto(); }
    public String tipo() { return "BAJO_COSTO"; }
}
