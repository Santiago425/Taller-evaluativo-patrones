package analizador;

public final class AnalizadorReferencia implements Analizador {
    private final double factor;
    public AnalizadorReferencia(double factor) { this.factor = factor; }
    public double corregir(double lectura, double humedad) { return lectura * factor; }
    public boolean dentroDeRango(double lectura) { return lectura >= 0 && lectura <= 500; }
    public String descripcion() { return "analizador de método de referencia"; }
}
