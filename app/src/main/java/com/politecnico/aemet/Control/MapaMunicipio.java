package com.politecnico.aemet.Control;

import com.politecnico.aemet.Model.Municipio;

import java.util.HashMap;
import java.util.List;

public class MapaMunicipio {

    HashMap<String, String> mapaLocalidad;

    public MapaMunicipio() {
        mapaLocalidad = new HashMap<>();
    }

    public HashMap<String, String> getMapa(List<Municipio> listaLocalidad) {
        mapaLocalidad = new HashMap<>();
        for (Municipio municipio : listaLocalidad) {
            mapaLocalidad.put(municipio.getNombre().toUpperCase(), municipio.getNumero().toString());
        }
        return mapaLocalidad;
    }

}