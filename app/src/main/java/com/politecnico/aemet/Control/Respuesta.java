package com.politecnico.aemet.Control;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.politecnico.aemet.Model.Tiempo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Locale;


public class Respuesta {
    //ESTADO
    protected String datos;
    protected String datosClima;

    public void setDatosClima(String datosClima) {
        this.datosClima = datosClima;
    }

    String NEW_URL;


    //COMPORTAMIENTO
    public Respuesta(String entrada) {
        datos = entrada;
    }

    public void getDias() {


        JsonObject jso = JsonParser.parseString(this.datos).getAsJsonObject();

        NEW_URL = jso.get("datos").getAsString();
        PeticionMunicipio ptm =new PeticionMunicipio();
        ptm.requestData(NEW_URL);

    }

    public LinkedList<Tiempo> getTiempo() {

        LinkedList<Tiempo> dataList = new LinkedList<>();

        JsonArray jsa = JsonParser.parseString(this.datosClima).getAsJsonArray();
        Log.d("Respuesta",this.datosClima);

        JsonObject jsonCompleto = jsa.get(0).getAsJsonObject();
        JsonObject jsonPre = jsonCompleto.getAsJsonObject("prediccion");
        JsonArray jsaDia = jsonPre.getAsJsonArray("dia");
        for (int i = 0; i < jsaDia.size(); i++) {
            JsonObject diaElement = jsaDia.get(i).getAsJsonObject();

            String fecha = diaElement.get("fecha").getAsString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            LocalDateTime fechaLocalDateTime = LocalDateTime.parse(fecha, formatter);
            DateTimeFormatter formatterDiaSemana = DateTimeFormatter.ofPattern("EEEE", new Locale("es", "ES"));
            String diaSemana = fechaLocalDateTime.format(formatterDiaSemana);

            JsonArray jsaEstadoCielo = diaElement.getAsJsonArray("estadoCielo");
            JsonObject estadoCieloElement = jsaEstadoCielo.get(0).getAsJsonObject();
            String estadoCielo = estadoCieloElement.get("descripcion").getAsString();
            Log.d("Respuesta Estados", estadoCielo);

            JsonObject tempElement = diaElement.getAsJsonObject("temperatura");
            String tempMax = tempElement.get("maxima").getAsString();
            String tempMin = tempElement.get("minima").getAsString();
            dataList.add(new Tiempo(estadoCielo,diaSemana,tempMax, tempMin));

        }
        return dataList;
    }
}



