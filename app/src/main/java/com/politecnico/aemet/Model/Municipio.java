package com.politecnico.aemet.Model;

public class Municipio {

    private String numero;
    private String nombre;

    public Municipio(String numero, String nombre) {
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

}
