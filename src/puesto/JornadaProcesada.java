package puesto;

import analizador.Analizador;
import modelo.LecturaCruda;
import java.util.*;

public final class JornadaProcesada {
    public record Resultado(String contaminante, double promedio, int validas, boolean suficiente, List<Double> corregidas) {}
    private final Map<String, Resultado> resultados;
    private final int descartadas;
    private final int esperadas;
    public JornadaProcesada(Map<String, Resultado> resultados, int descartadas, int esperadas) { this.resultados = Map.copyOf(resultados); this.descartadas = descartadas; this.esperadas = esperadas; }
    public Map<String, Resultado> resultados() { return resultados; }
    public int descartadas() { return descartadas; }
    public int esperadas() { return esperadas; }
    public static JornadaProcesada procesar(List<LecturaCruda> lecturas, Map<String, Analizador> analizadores, Collection<String> contaminantes) {
        Map<String,List<Double>> corregidas = new HashMap<>();
        for (String c : contaminantes) corregidas.put(c, new ArrayList<>());
        int descartadas = 0;
        for (LecturaCruda l : lecturas) {
            Analizador a = analizadores.get(l.contaminante());
            if (a == null || !a.dentroDeRango(l.valor())) { descartadas++; continue; }
            corregidas.computeIfAbsent(l.contaminante(), x -> new ArrayList<>()).add(a.corregir(l.valor(), l.humedad()));
        }
        Map<String,Resultado> salida = new HashMap<>();
        for (String c : contaminantes) {
            List<Double> valores = corregidas.getOrDefault(c, List.of());
            double suma = valores.stream().mapToDouble(Double::doubleValue).sum();
            salida.put(c, new Resultado(c, valores.isEmpty() ? 0 : suma / valores.size(), valores.size(), valores.size() >= 18, List.copyOf(valores)));
        }
        return new JornadaProcesada(salida, descartadas, contaminantes.size() * 24);
    }
}
