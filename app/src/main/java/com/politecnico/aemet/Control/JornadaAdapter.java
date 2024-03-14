package com.politecnico.aemet.Control;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.politecnico.aemet.Model.Tiempo;
import com.politecnico.aemet.R;
import java.util.LinkedList;


public class JornadaAdapter extends RecyclerView.Adapter<JornadaViewHolder> {

    private final LinkedList<Tiempo> mList;
    private LayoutInflater mInflater;

    public JornadaAdapter(Context context,
                          LinkedList<Tiempo> list) {
        mInflater = LayoutInflater.from(context);
        this.mList = list;
    }


    @NonNull
    @Override
    public JornadaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.dias_item,
                parent, false);
        return new JornadaViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull JornadaViewHolder holder, int position) {
        //TODO: fill data
        holder.setEstadoCielo(this.mList.get(position).getEstado());
        holder.setDia(this.mList.get(position).getNombreDia());
        holder.setTemp(this.mList.get(position).getTemp());
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

}
