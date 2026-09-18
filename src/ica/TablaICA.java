package ica;

import modelo.PuntoICA;
import java.util.*;

public final class TablaICA {
    private final Map<String,List<PuntoICA>> tramos = new HashMap<>();
    public TablaICA() {
        tramos.put("PM2.5", List.of(new PuntoICA(0,12,0,50,"Buena"),new PuntoICA(12.1,37.4,51,100,"Aceptable"),new PuntoICA(37.5,55.4,101,150,"Dañina a grupos sensibles"),new PuntoICA(55.5,150.4,151,200,"Dañina a la salud"),new PuntoICA(150.5,250.4,201,300,"Muy dañina")));
        tramos.put("O3", List.of(new PuntoICA(0,54,0,50,"Buena"),new PuntoICA(55,70,51,100,"Aceptable"),new PuntoICA(71,85,101,150,"Dañina a grupos sensibles"),new PuntoICA(86,105,151,200,"Dañina a la salud"),new PuntoICA(106,200,201,300,"Muy dañina")));
        tramos.put("NO2", List.of(new PuntoICA(0,53,0,50,"Buena"),new PuntoICA(54,100,51,100,"Aceptable"),new PuntoICA(101,360,101,150,"Dañina a grupos sensibles"),new PuntoICA(361,649,151,200,"Dañina a la salud"),new PuntoICA(650,1249,201,300,"Muy dañina")));
    }
    public int calcular(String contaminante, double concentracion) {
        List<PuntoICA> puntos = tramos.get(contaminante); if (puntos == null) return 0;
        PuntoICA p = puntos.get(puntos.size()-1);
        for (PuntoICA candidato : puntos) if (concentracion <= candidato.cHi()) { p=candidato; break; }
        if (concentracion <= p.cLo()) return p.iLo();
        return (int)Math.round(((p.iHi()-p.iLo())/(p.cHi()-p.cLo()))*(concentracion-p.cLo())+p.iLo());
    }
    public String categoria(int indice) { if (indice <= 50) return "Buena"; if (indice <= 100) return "Aceptable"; if (indice <= 150) return "Dañina a grupos sensibles"; if (indice <= 200) return "Dañina a la salud"; return "Muy dañina"; }
}
