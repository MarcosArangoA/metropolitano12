package com.example.metropolitano12.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metropolitano12.Controlador.UsuarioDAOImpl;
import com.example.metropolitano12.Modelo.usuarios;
import com.example.metropolitano12.R;


public class register extends AppCompatActivity {

    EditText etDni, etContrasena, etNombre, etApellido, etCorreo;
    Button btnRegistrar;
    ImageButton btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etDni = findViewById(R.id.etDni);
        etContrasena = findViewById(R.id.etContrasena);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCorreo = findViewById(R.id.etCorreo);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnAtras = findViewById(R.id.btnAtras);

        btnAtras.setOnClickListener(view -> {
            Intent intent = new Intent(register.this, MainActivity.class);
            startActivity(intent);
        });

        btnRegistrar.setOnClickListener(view -> {
            String dni = etDni.getText().toString();
            String contraseña = etContrasena.getText().toString();
            String nombre = etNombre.getText().toString();
            String apellido = etApellido.getText().toString();
            String correo = etCorreo.getText().toString();
            double saldo = 0.0;

            if (dni.isEmpty() || contraseña.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
                Toast.makeText(register.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            usuarios nuevoUsuario = new usuarios(dni, nombre, apellido, contraseña, correo, saldo);
            UsuarioDAOImpl dao = new UsuarioDAOImpl(getApplicationContext());

            boolean registrado = dao.registrarUsuario(nuevoUsuario);

            if (registrado) {
                Toast.makeText(register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(register.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(register.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
