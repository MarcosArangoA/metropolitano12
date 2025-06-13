package com.example.metropolitano12.Vista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.metropolitano12.Modelo.Recarga;
import com.example.metropolitano12.R;

import java.util.List;

public class RecargaAdapter extends RecyclerView.Adapter<RecargaAdapter.ViewHolder> {

    private List<Recarga> listaRecargas;

    public RecargaAdapter(List<Recarga> listaRecargas) {
        this.listaRecargas = listaRecargas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdRecarga, tvMonto;

        public ViewHolder(View itemView) {
            super(itemView);
            tvIdRecarga = itemView.findViewById(R.id.tvIdRecarga);
            tvMonto = itemView.findViewById(R.id.tvMonto);
        }
    }

    @Override
    public RecargaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recarga, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecargaAdapter.ViewHolder holder, int position) {
        Recarga recarga = listaRecargas.get(position);
        holder.tvIdRecarga.setText(String.valueOf(recarga.getIdRecarga()));
        holder.tvMonto.setText("S/" + String.valueOf(recarga.getMonto()));
    }

    @Override
    public int getItemCount() {
        return listaRecargas.size();
    }
}
