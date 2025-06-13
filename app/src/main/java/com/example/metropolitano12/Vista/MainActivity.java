package com.example.metropolitano12.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metropolitano12.Controlador.UsuarioDAOImpl;
import com.example.metropolitano12.Modelo.usuarios;
import com.example.metropolitano12.R;

public class MainActivity extends AppCompatActivity {

    EditText etDni, etContrasena;
    Button btnIniciarSesion, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etDni = findViewById(R.id.etDni);
        etContrasena = findViewById(R.id.etContrasena);
        btnIniciarSesion = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        UsuarioDAOImpl dao = new UsuarioDAOImpl(getApplicationContext());


        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = etDni.getText().toString().trim();
                String contraseña = etContrasena.getText().toString().trim();

                if (dni.isEmpty() || contraseña.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (dao.login(dni, contraseña)) {
                    usuarios usuario = dao.buscarPorDni(dni);
                    int idUsuario = usuario.getIdUsuario();

                    Intent intent = new Intent(MainActivity.this, menu.class);
                    intent.putExtra("id", idUsuario); // Pasas el ID al menú
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });
    }
}
