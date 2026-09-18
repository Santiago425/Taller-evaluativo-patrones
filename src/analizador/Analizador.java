package analizador;

public interface Analizador {
    double corregir(double lectura, double humedad);
    boolean dentroDeRango(double lectura);
    String descripcion();
}
