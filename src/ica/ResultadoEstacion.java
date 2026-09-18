package ica;

import puesto.JornadaProcesada;
import java.util.*;
public record ResultadoEstacion(String nombre, int ica, String categoria, String critico, int validas, int esperadas) {
    public static ResultadoEstacion calcular(String nombre, JornadaProcesada jornada, TablaICA tabla) {
        int max = 0; String critico = "N/A"; int validas = 0;
        for (JornadaProcesada.Resultado r : jornada.resultados().values()) if (r.suficiente()) { int valor = tabla.calcular(r.contaminante(), r.promedio()); validas += r.validas(); if (valor > max) { max=valor; critico=r.contaminante(); } }
        return new ResultadoEstacion(nombre,max,tabla.categoria(max),critico,validas,jornada.esperadas());
    }
}
