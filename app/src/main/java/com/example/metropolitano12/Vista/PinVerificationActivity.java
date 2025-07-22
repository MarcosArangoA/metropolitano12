// Archivo: PinVerificationActivity.java
package com.example.metropolitano12.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metropolitano12.Controlador.UsuarioDAOImpl;
import com.example.metropolitano12.Modelo.usuarios;
import com.example.metropolitano12.R;

public class PinVerificationActivity extends AppCompatActivity {

    private EditText etPinDisplay;
    private GridLayout keypad;
    private StringBuilder pinBuilder = new StringBuilder();
    private int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_verification);

        // Referencias a vistas
        etPinDisplay = findViewById(R.id.etPinDisplay);
        keypad       = findViewById(R.id.keypad);

        // Obtener el ID de usuario pasado desde el login
        idUsuario = getIntent().getIntExtra("id", -1);

        // Asignar listener a cada botón del GridLayout
        for (int i = 0; i < keypad.getChildCount(); i++) {
            View v = keypad.getChildAt(i);
            if (v instanceof Button) {
                v.setOnClickListener(this::onKeyPress);
            }
        }

        // Inicializar display
        refreshDisplay();
    }

    /**
     * Maneja la pulsación en cada tecla numérica, borrar o confirmar.
     */
    private void onKeyPress(View view) {
        String tag = (String) view.getTag();

        switch (tag) {
            case "delete":
                if (pinBuilder.length() > 0) {
                    pinBuilder.deleteCharAt(pinBuilder.length() - 1);
                }
                break;

            case "confirm":
                attemptVerify();
                return;

            default:
                // Agregar dígito si quedan menos de 6
                if (pinBuilder.length() < 6) {
                    pinBuilder.append(tag);
                }
                break;
        }

        refreshDisplay();
    }

    /**
     * Actualiza el EditText mostrando ● para cada dígito ingresado y – para los pendientes.
     */
    private void refreshDisplay() {
        StringBuilder display = new StringBuilder();
        int entered = pinBuilder.length();

        for (int i = 0; i < 6; i++) {
            display.append(i < entered ? '●' : '–');
        }

        etPinDisplay.setText(display.toString());
    }

    /**
     * Verifica el PIN con el almacenado en la base de datos.
     * Si coincide, redirige al menú; si no, limpia e indica error.
     */
    private void attemptVerify() {
        if (pinBuilder.length() != 6) {
            Toast.makeText(this, "Ingresa los 6 dígitos del PIN", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener usuario de la base de datos
        UsuarioDAOImpl dao = new UsuarioDAOImpl(this);
        usuarios u = dao.buscarPorId(idUsuario);

        if (u != null && pinBuilder.toString().equals(u.getTvPin())) {
            // PIN correcto: ir al menú
            Intent intent = new Intent(this, menu.class);
            intent.putExtra("id", idUsuario);
            startActivity(intent);
            finish();
        } else {
            // PIN incorrecto: mensaje y reiniciar entrada
            Toast.makeText(this, "PIN incorrecto", Toast.LENGTH_SHORT).show();
            pinBuilder.setLength(0);
            refreshDisplay();
        }
    }
}
