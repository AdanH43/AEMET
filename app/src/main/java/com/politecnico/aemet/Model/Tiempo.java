package com.politecnico.aemet.Model;

public class Tiempo {
    private String estado;
    private String nombreDia;
    private String tempMax;
    private String tempMin;

    public Tiempo(String estado, String nombreDia, String tempMax, String tempMin) {
        this.estado = estado;
        this.nombreDia = nombreDia;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    public String getEstado() {
        return estado;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public String getTemp() {
        String Tempe = "Temperatura:" + "\n" + "Max: " +tempMax+ "cº" + "  " + "Min: " +tempMin +"cº";
        return Tempe;
    }

}
