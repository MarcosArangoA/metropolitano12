package com.example.metropolitano12.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metropolitano12.Controlador.BD;
import com.example.metropolitano12.Controlador.UsuarioDAOImpl;
import com.example.metropolitano12.Modelo.usuarios;
import com.example.metropolitano12.R;

public class menu extends AppCompatActivity {
    TextView txtSaldo;
    Button btnRecargas;
    ImageButton btnHistorial;
    Bundle datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        txtSaldo = findViewById(R.id.txtSaldo);
        btnRecargas = findViewById(R.id.btnRecargas);
        btnHistorial = findViewById(R.id.bnCuenta);
        datos = getIntent().getExtras();
        int id= datos.getInt("id");


        final BD bd=new BD(getApplicationContext());
        UsuarioDAOImpl dao = new UsuarioDAOImpl(getApplicationContext());
        usuarios usuario = dao.buscarPorId(id);
        if (usuario != null) {
            txtSaldo.setText(String.valueOf(usuario.getTvSaldo()));
        }

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, HistorialRecargas.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        btnRecargas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, Recargas.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}