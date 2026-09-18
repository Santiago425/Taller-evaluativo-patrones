package analizador;

public final class AnalizadorMovil implements Analizador {
    public double corregir(double lectura, double humedad) { return lectura * 1.05 + 0.8; }
    public boolean dentroDeRango(double lectura) { return lectura >= 0 && lectura <= 500; }
    public String descripcion() { return "analizador óptico portátil"; }
}
