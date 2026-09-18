package boletin;

import ica.ResultadoEstacion;
import java.time.LocalDate;
import java.util.*;

public final class BoletinDiario {
    private final LocalDate fecha; private final String entidad; private final List<ResultadoEstacion> estaciones; private final int maximo; private final String categoria; private final String recomendaciones, pronostico, anexo, responsable, nota;
    private BoletinDiario(Builder b) { fecha=b.fecha; entidad=b.entidad; estaciones=List.copyOf(b.estaciones); maximo=b.maximo; categoria=b.categoria; recomendaciones=b.recomendaciones; pronostico=b.pronostico; anexo=b.anexo; responsable=b.responsable; nota=b.nota; }
    public void publicar() { System.out.println("--- BOLETIN DIARIO " + fecha + " ---"); int i=1; for (ResultadoEstacion e: estaciones) System.out.printf("%d %s ICA %d %s (%s)%n",i++,e.nombre(),e.ica(),e.categoria(),e.critico()); System.out.printf("ICA de la ciudad: %d (%s)%n",maximo,categoria); int validas=estaciones.stream().mapToInt(ResultadoEstacion::validas).sum(), esperadas=estaciones.stream().mapToInt(ResultadoEstacion::esperadas).sum(); System.out.printf("Cobertura de datos validos de la red: %.1f%%%n",100.0*validas/esperadas); if(recomendaciones!=null) System.out.println("Recomendaciones: "+recomendaciones); }
    public static class Builder {
        private LocalDate fecha; private String entidad, categoria; private List<ResultadoEstacion> estaciones=new ArrayList<>(); private int maximo=-1; private String recomendaciones,pronostico,anexo,responsable,nota;
        public Builder fecha(LocalDate v){fecha=v;return this;} public Builder entidadEmisora(String v){entidad=v;return this;} public Builder resultados(List<ResultadoEstacion> v){estaciones=new ArrayList<>(v);return this;} public Builder icaMaximo(int v){maximo=v;return this;} public Builder categoria(String v){categoria=v;return this;} public Builder recomendaciones(String v){recomendaciones=v;return this;} public Builder pronostico(String v){pronostico=v;return this;} public Builder anexo(String v){anexo=v;return this;} public Builder responsable(String v){responsable=v;return this;} public Builder notaMetodologica(String v){nota=v;return this;}
        public BoletinDiario build(){if(fecha==null||entidad==null||estaciones.isEmpty()||maximo<0||categoria==null) throw new IllegalStateException("Falta un obligatorio o la lista de estaciones está vacía"); if((categoria.equals("Dañina a la salud")||categoria.equals("Muy dañina"))&& (recomendaciones==null||recomendaciones.isBlank())) throw new IllegalStateException("Se requieren recomendaciones para población sensible"); return new BoletinDiario(this);}
    }
}
