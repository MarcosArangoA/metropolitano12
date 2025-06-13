package com.example.metropolitano12.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metropolitano12.R;

public class Recargas extends AppCompatActivity {
EditText etMonto;
Button btnRecargar;
Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recargas);
        btnRecargar = findViewById(R.id.btnRecargar);
        etMonto = findViewById(R.id.etMonto);

        datos = getIntent().getExtras();
        int id= datos.getInt("id");


        btnRecargar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), card_form.class);
                intent.putExtra("id", id);
                intent.putExtra("monto",etMonto.getText().toString());
                startActivity(intent);
            }
        });
    }
}