package com.politecnico.aemet.View;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.politecnico.aemet.Control.CSVLector;
import com.politecnico.aemet.Control.JornadaAdapter;
import com.politecnico.aemet.Control.JornadaListViewModel;
import com.politecnico.aemet.Control.MainController;
import com.politecnico.aemet.Control.MapaMunicipio;
import com.politecnico.aemet.Model.Municipio;
import com.politecnico.aemet.Model.Tiempo;
import com.politecnico.aemet.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private LinkedList<Tiempo> mList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private JornadaAdapter mAdapter;
    private Map<String, String> mapaMunicipio = new HashMap<>();
    private static MainActivity myActiveActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            mRecyclerView = findViewById(R.id.rv_tiempo);
            mAdapter = new JornadaAdapter(this, mList);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));





        MainController.setURL();
        MainActivity.myActiveActivity = this;
        MainController.setActivity(this);


        List<Municipio> csvData = CSVLector.LeerCSV(this, R.raw.diccionario);
        List<String> elementosSpinner = new ArrayList<>();
        EditText busqueda =findViewById(R.id.ed_localidades);

        Spinner spinnerLocalidad =findViewById(R.id.sp_localidades);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, elementosSpinner);
        spinnerLocalidad.setAdapter(adapter);

        mapaMunicipio = new MapaMunicipio().getMapa(csvData);

        busqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() >= 3) {
                    elementosSpinner.clear();
                    for (Map.Entry<String, String> map : mapaMunicipio.entrySet()) {
                        String nombre = map.getKey();
                        if (nombre.startsWith(busqueda.getText().toString().toUpperCase())) {
                            elementosSpinner.add(nombre);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        spinnerLocalidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0 && position < elementosSpinner.size()) {
                    String selectedElement = elementosSpinner.get(position);
                    MainController.setCodigo(mapaMunicipio.get(selectedElement));
                    MainController.getSingleton().requestDataFromAemet();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void accessData() {
        List<Tiempo> nuevaLista = MainController.getSingleton().getDataFromAemet();
        mList.clear();
        for (Tiempo item : nuevaLista) {
            mList.add(item);
        }
        mAdapter.notifyDataSetChanged();
        TextView tv = findViewById(R.id.textView);
    }

    public void errorData(String error) {
        TextView tv = findViewById(R.id.textView);
        tv.setText(error);
    }
}
