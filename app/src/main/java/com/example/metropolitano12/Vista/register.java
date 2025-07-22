package com.example.metropolitano12.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metropolitano12.Controlador.UsuarioDAOImpl;
import com.example.metropolitano12.Utils.Seguridad;
import com.example.metropolitano12.Modelo.usuarios;
import com.example.metropolitano12.R;

public class register extends AppCompatActivity {

    EditText etDni, etContrasena, etNombre, etApellido, etCorreo, etPin;
    Button btnRegistrar;
    ImageButton btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etDni         = findViewById(R.id.etDni);
        etContrasena  = findViewById(R.id.etContrasena);
        etNombre      = findViewById(R.id.etNombre);
        etApellido    = findViewById(R.id.etApellido);
        etCorreo      = findViewById(R.id.etCorreo);
        etPin         = findViewById(R.id.etPin);               // PIN display

        btnRegistrar  = findViewById(R.id.btnRegistrar);
        btnAtras      = findViewById(R.id.btnAtras);

        // Generar PIN al entrar en la pantalla
        String pinGenerado = Seguridad.generarCodigo2FA();
        etPin.setText(pinGenerado);

        btnAtras.setOnClickListener(view -> {
            startActivity(new Intent(register.this, MainActivity.class));
        });

        btnRegistrar.setOnClickListener(view -> {
            String dni        = etDni.getText().toString().trim();
            String contraseña = etContrasena.getText().toString().trim();
            String nombre     = etNombre.getText().toString().trim();
            String apellido   = etApellido.getText().toString().trim();
            String correo     = etCorreo.getText().toString().trim();
            String pin        = etPin.getText().toString();     // PIN fijo

            if (dni.isEmpty() || contraseña.isEmpty() ||
                    nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
                Toast.makeText(register.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crea usuario con PIN autogenerado
            usuarios nuevoUsuario = new usuarios(dni, nombre, apellido, contraseña, correo, 0.0);
            nuevoUsuario.setTvPin(pin);

            UsuarioDAOImpl dao = new UsuarioDAOImpl(getApplicationContext());
            boolean registrado = dao.registrarUsuario(nuevoUsuario);

            if (registrado) {
                Toast.makeText(register.this, "Registro exitoso. Tu PIN es: " + pin, Toast.LENGTH_LONG).show();
                startActivity(new Intent(register.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(register.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
