package com.politecnico.aemet.Control;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Clase Peticion
 *
 * Es utilizado por el controlador. El controlador le proporciona
 * los datos necesarios
 *
 * Se apoyará en OkHttp (librería cliente http/http2)
 *
 */
public class Peticion {
    //ESTADO
    //Clase utilidad que no necesita nada más que poner a funcionar la peticion HTTPs
    private static final String API_KEY = "?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZGFuaGlndWVyYXNtZW5kb3phQGdtYWlsLmNvbSIsImp0aSI6IjViZTIwMWViLWYzMzctNGY0MS04ZTgxLTBiMzZmNTY4OTBmNSIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNzAyNTMzMDc4LCJ1c2VySWQiOiI1YmUyMDFlYi1mMzM3LTRmNDEtOGU4MS0wYjM2ZjU2ODkwZjUiLCJyb2xlIjoiIn0.ssSQaEUgbKM9KHfNb2HL16pxUfYw2N2KJmLeONfFZkw";
    //COMPORTAMIENTO
    public Peticion() {

    }

    public void requestData(String URL) {
        OkHttpClient cliente = new OkHttpClient();

        //construimos la peticion
        Request peticion = new Request.Builder()
                .url(URL + API_KEY)
                .get()
                .addHeader("cache-control", "no-cache")
                .build();


        //realizamos la llamada al server, pero en otro thread (con enqueue)
         Call llamada = cliente.newCall(peticion);
         llamada.enqueue(new Callback() {
             public void onResponse(Call call, Response respuestaServer)
                     throws IOException {
                 //Got answer!!!!!
                 String respuesta = respuestaServer.body().string();
                 // Create a handler that associated with Looper of the main thread
                 Handler manejador = new Handler(Looper.getMainLooper());
// Send a task to the MessageQueue of the main thread
                 manejador.post(new Runnable() {
                     @Override
                     public void run() {
                         // Code will be executed on the main thread
                         MainController.getSingleton().setDataFromAemet(respuesta);
                     }
                 });
             }

             public void onFailure(Call call, IOException e) {
                 String respuesta = e.getMessage();
                 Handler manejador = new Handler(Looper.getMainLooper());

// Send a task to the MessageQueue of the main thread
                 manejador.post(new Runnable() {
                     @Override
                     public void run() {
                         // Code will be executed on the main thread
                         MainController.getSingleton().setDataFromAemet("");
                         MainController.getSingleton().setErrorFromAemet(respuesta);
                     }
                 });
             }
         });





    }

}
