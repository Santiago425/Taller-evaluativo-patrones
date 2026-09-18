package puesto;

import analizador.*;
import configuracion.ConfiguracionEstacion;
public final class PuestoMovil extends PuestoDeMonitoreo {
    public PuestoMovil(String n, ConfiguracionEstacion c) { super(n,c); }
    protected Analizador crearAnalizador(String c) { return new AnalizadorMovil(); }
    public String tipo() { return "MOVIL"; }
}
