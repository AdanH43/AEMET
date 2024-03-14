package com.politecnico.aemet.Control;

import android.content.Context;
import android.content.res.Resources;

import com.politecnico.aemet.Model.Municipio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVLector {
    public static List<Municipio> LeerCSV(Context context, int resourceId) {
        List<Municipio> listaLocalidad = new ArrayList<>();
        try {
            Resources resources = context.getResources();
            InputStream inputStream = resources.openRawResource(resourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] Parte = linea.split(";");
                Municipio nuevo = new Municipio(Parte[0]+Parte[1],Parte[2]);
                listaLocalidad.add(nuevo);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaLocalidad;
    }
}
