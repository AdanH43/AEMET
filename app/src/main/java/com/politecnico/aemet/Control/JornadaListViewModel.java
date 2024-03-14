package com.politecnico.aemet.Control;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.politecnico.aemet.Model.Tiempo;

import java.util.List;

public class JornadaListViewModel extends ViewModel {
    private MutableLiveData<List<Tiempo>> listaTiempo;

    public LiveData<List<Tiempo>> getTiempo() {
        if(listaTiempo == null) {
            listaTiempo = new MutableLiveData<List<Tiempo>>();
            loadTiempo();

        }
        return listaTiempo;
    }

    private void loadTiempo() {
        MainController.getSingleton().requestDataFromAemet();
        listaTiempo.postValue(MainController.getSingleton().getDataFromAemet());
    }

    public void setData(List<Tiempo> list) {
        listaTiempo.postValue(list);
    }
}
