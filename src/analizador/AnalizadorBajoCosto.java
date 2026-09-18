package analizador;

public final class AnalizadorBajoCosto implements Analizador {
    public double corregir(double lectura, double humedad) {
        return humedad > 75 ? lectura / (1 + 0.012 * (humedad - 75)) : lectura;
    }
    public boolean dentroDeRango(double lectura) { return lectura >= 0 && lectura <= 500; }
    public String descripcion() { return "sensor con corrección por humedad"; }
}
