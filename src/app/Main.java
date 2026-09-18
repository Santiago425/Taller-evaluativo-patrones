package app;

import boletin.BoletinDiario;
import configuracion.*;
import ica.*;
import modelo.LecturaCruda;
import puesto.*;
import java.time.LocalDate;
import java.util.*;

public final class Main {
    private static List<LecturaCruda> lecturas(double pm, double oz, double no2, boolean bajoCosto, int faltantes) {
        List<LecturaCruda> r=new ArrayList<>(); for(int h=0;h<24-faltantes;h++){double hr=bajoCosto?88+(h%5):55; r.add(new LecturaCruda("PM2.5",h,pm+(h%3),hr)); r.add(new LecturaCruda("O3",h,oz+(h%4),hr)); r.add(new LecturaCruda("NO2",h,no2+(h%2),hr));} r.add(new LecturaCruda("PM2.5",23,-3,50)); r.add(new LecturaCruda("O3",22,999,50)); return r; }
    public static void main(String[] args) {
        System.out.println("=== SECRETARIA DE AMBIENTE - RED DE CALIDAD DEL AIRE ===");
        ConfiguracionEstacion modelo=new ConfiguracionEstacion("Zona residencial",60,List.of("PM2.5","O3","NO2"),Map.of("PM2.5",37.5,"O3",70.0,"NO2",100.0),new ArrayList<>(List.of(new TareaMantenimiento("Calibrar",30),new TareaMantenimiento("Limpiar",7),new TareaMantenimiento("Inspeccionar",15))));
        ConfiguracionEstacion parque=modelo.clone(), colegio=modelo.clone(); colegio.cambiarUmbral("PM2.5",25); colegio.agregarTarea(new TareaMantenimiento("Revisar humedad",10));
        System.out.printf("Config modelo '%s' umbral PM2.5=%.1f tareas: %d%n",modelo.zona(),modelo.umbrales().get("PM2.5"),modelo.tareas().size()); System.out.printf("Estacion Parque Centenario umbral PM2.5=%.1f tareas: %d%n",parque.umbrales().get("PM2.5"),parque.tareas().size()); System.out.printf("Estacion Colegio San Jose umbral PM2.5=%.1f tareas: %d <- modificada%n",colegio.umbrales().get("PM2.5"),colegio.tareas().size()); System.out.printf("Verificacion del modelo -> umbral PM2.5=%.1f tareas: %d (intacto)%n",modelo.umbrales().get("PM2.5"),modelo.tareas().size());
        List<PuestoDeMonitoreo> puestos=List.of(new PuestoFijoReferencia("Parque Centenario",parque),new PuestoFijoReferencia("Av. Quebradaseca",parque),new PuestoMovil("Unidad movil - Zona Ind.",parque),new PuestoBajoCosto("Colegio San Jose",colegio));
        List<ResultadoEstacion> resultados=new ArrayList<>(); TablaICA tabla=new TablaICA(); System.out.println("--- PROCESAMIENTO POR ESTACION ---");
        for(int i=0;i<puestos.size();i++){PuestoDeMonitoreo p=puestos.get(i); JornadaProcesada j=p.procesarJornada(lecturas(35+i*10,45+i*8,50+i*20,p instanceof PuestoBajoCosto,i==3?9:0)); System.out.printf("[%s] tipo %s%n",p.nombre(),p.tipo()); if(p instanceof PuestoBajoCosto) System.out.println("h07 cruda 44.20 HR 88 % -> corregida "+String.format("%.2f",new analizador.AnalizadorBajoCosto().corregir(44.2,88))); System.out.println("lecturas descartadas: "+j.descartadas()); for(JornadaProcesada.Resultado x:j.resultados().values()) System.out.printf("%s validas: %d de 24 -> %s%n",x.contaminante(),x.validas(),x.suficiente()?"VALIDO":"DATO INSUFICIENTE"); resultados.add(ResultadoEstacion.calcular(p.nombre(),j,tabla)); }
        resultados.sort(Comparator.comparingInt(ResultadoEstacion::ica).reversed().thenComparing(ResultadoEstacion::nombre)); int max=resultados.get(0).ica(); String cat=tabla.categoria(max); try{new BoletinDiario.Builder().fecha(LocalDate.of(2026,8,14)).entidadEmisora("Secretaría de Ambiente").resultados(resultados).icaMaximo(max).categoria("Dañina a la salud").build();}catch(IllegalStateException e){System.out.println("Excepción esperada: "+e.getMessage());} new BoletinDiario.Builder().fecha(LocalDate.of(2026,8,14)).entidadEmisora("Secretaría de Ambiente").resultados(resultados).icaMaximo(max).categoria(cat).recomendaciones("Personas con asma y niños deben evitar actividad física al aire libre.").responsable("Secretaría de Ambiente").build().publicar();
    }
}
