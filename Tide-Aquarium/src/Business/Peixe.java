package Business;

import java.time.LocalDate;

public class Peixe {
    private static int novoId = 0;

    private int id;
    private int usuarioId; //Relação N:1 com Usuario
    private int aquarioId; //Relação N:1 com Aquario
    private String especie;
    private TipoAgua tipo;
    private double phMin;
    private double phMax;
    private double temperatura;
    private LocalDate dtChegada;

    private Peixe(){
        id = novoId++;
    }

    private Peixe(int usuarioId, int aquarioId, String especie, TipoAgua tipo, double phMin, double phMax, double temperatura, LocalDate dtChegada){
        this();
        this.usuarioId = usuarioId;
        this.aquarioId = aquarioId;
        this.especie = especie;
        this.tipo = tipo;
        this.phMin = phMin;
        this.phMax = phMax;
        this.temperatura = temperatura;
        this.dtChegada = dtChegada;
    }

    public Peixe(Peixe outro){
        this.id = outro.id;
        this.usuarioId = outro.usuarioId;
        this.aquarioId = outro.aquarioId;
        this.especie = outro.especie;
        this.tipo = outro.tipo;
        this.phMin = outro.phMin;
        this.phMax = outro.phMax;
        this.temperatura = outro.temperatura;
        this.dtChegada = outro.dtChegada;
    }

    public static Peixe getInstance(int usuarioId, int aquarioId, String especie, TipoAgua tipo, double phMin, double phMax, double temperatura, LocalDate dtChegada){
        if (usuarioId >= 0 && aquarioId >= 0 && !especie.isBlank() && tipo != null && dtChegada != null
            && phMin > 0 && phMin < 14 && phMax > 0 && phMax < 14)
            return new Peixe(usuarioId, aquarioId, especie, tipo, phMin, phMax, temperatura, dtChegada);
        return null;
    }

}
