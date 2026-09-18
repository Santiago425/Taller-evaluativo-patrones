package configuracion;

public final class TareaMantenimiento implements Cloneable {
    private String descripcion;
    private int periodicidadDias;
    public TareaMantenimiento(String descripcion, int periodicidadDias) { this.descripcion = descripcion; this.periodicidadDias = periodicidadDias; }
    public String descripcion() { return descripcion; }
    public int periodicidadDias() { return periodicidadDias; }
    public TareaMantenimiento clone() { return new TareaMantenimiento(descripcion, periodicidadDias); }
    public String toString() { return descripcion + " (cada " + periodicidadDias + " días)"; }
}
