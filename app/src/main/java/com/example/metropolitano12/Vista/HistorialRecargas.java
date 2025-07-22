package com.example.metropolitano12.Vista;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.metropolitano12.Controlador.RecargaDAOImpl;
import com.example.metropolitano12.Modelo.Recarga;
import com.example.metropolitano12.R;

import java.util.List;

public class HistorialRecargas extends AppCompatActivity {

    RecyclerView recyclerView;
    RecargaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recargas);

        recyclerView = findViewById(R.id.rvRecargas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int idUsuario = getIntent().getIntExtra("id", -1);

        if (idUsuario != -1) {
            RecargaDAOImpl dao = new RecargaDAOImpl(this);
            List<Recarga> recargas = dao.obtenerRecargasPorUsuario(idUsuario);

            adapter = new RecargaAdapter(recargas);
            recyclerView.setAdapter(adapter);
        }
    }
}
