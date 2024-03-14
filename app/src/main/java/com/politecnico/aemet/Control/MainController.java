package com.politecnico.aemet.Control;

import com.politecnico.aemet.Model.Tiempo;

import com.politecnico.aemet.View.MainActivity;

import java.util.LinkedList;
import java.util.List;
public class MainController {
    private static MainController mySingleController;

    //SINGLETON Controller
    private static String DATA_URL;
    private static String DATA_CODMUN;
    private static Respuesta answer;
    private List<Tiempo> dataRequested;
    private static MainActivity activeActivity;
    //Comportamiento
    //Constructor
    private MainController() {
        dataRequested = new LinkedList<Tiempo>();
    }

    public static void setURL() {
        DATA_URL = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/";
    }
    public static void setCodigo(String codigo) {
        DATA_CODMUN = codigo;
    }
    //Get instance
    public static MainController getSingleton() {
        if (MainController.mySingleController == null) {
            mySingleController = new MainController();
        }
        return mySingleController;
    }

    //To send data to view
    public List<Tiempo> getDataFromAemet() {
        return this.dataRequested;
    }

    public static String getDataUrl() {
        return DATA_URL;
    }
    //Called from the view
    public void requestDataFromAemet() {
        Peticion p = new Peticion();
        p.requestData(DATA_URL+DATA_CODMUN);
    }

    //Called when onResponse is OK
    public void setDataFromAemet(String json) {

        answer = new Respuesta(json);
        answer.getDias();
        //Load data on the list
        MainController.activeActivity.accessData();
    }
    public void setDataFromAemet2(String json) {
        answer.setDatosClima(json);
        dataRequested = answer.getTiempo();
        //Load data on the list
        MainController.activeActivity.accessData();
    }
    public void setErrorFromAemet(String error) {

        //Load data on the list
        MainController.activeActivity.errorData(error);
    }


    public static void setActivity(MainActivity myAct) {
        activeActivity = myAct;
    }

}