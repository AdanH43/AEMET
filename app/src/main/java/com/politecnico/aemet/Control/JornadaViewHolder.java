package com.politecnico.aemet.Control;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.politecnico.aemet.R;

public class JornadaViewHolder extends RecyclerView.ViewHolder {

    final JornadaAdapter adapter;

    private ImageView ivEstadoCielo;
    private TextView tvDia;
    private TextView tvTemp;

    public JornadaViewHolder(View itemView, JornadaAdapter adapter) {
        super(itemView);
        ivEstadoCielo = itemView.findViewById(R.id.ivEstadoCielo);
        tvDia = itemView.findViewById(R.id.tvDia);
        tvTemp = itemView.findViewById(R.id.tvTemperatura);
        this.adapter = adapter;
    }

    public void setEstadoCielo(String estadoCielo) {
        int icono;
        switch (estadoCielo) {
            case "Despejado":
                icono = R.drawable.dom;
                break;
            case "Poco nuboso":
            case "Intervalos nubosos":
                icono = R.drawable.poconublado;
                break;
            case "Nuboso":
            case "Muy nuboso":
                icono = R.drawable.nube;
                break;
            case "Cubierto con lluvia":
                icono = R.drawable.lluvia;
                break;
            default:
                icono = R.drawable.dom;
                break;
        }
        ivEstadoCielo.setImageResource(icono);
    }

    public void setDia(String dia) {
        tvDia.setText(dia);
    }

    public void setTemp(String temp) {
        tvTemp.setText(temp);
    }
}
