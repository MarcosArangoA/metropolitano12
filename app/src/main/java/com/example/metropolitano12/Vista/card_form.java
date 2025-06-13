package com.example.metropolitano12.Vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.braintreepayments.cardform.view.CardForm;
import com.example.metropolitano12.Controlador.BD;
import com.example.metropolitano12.Controlador.RecargaDAOImpl;
import com.example.metropolitano12.Controlador.UsuarioDAOImpl;
import com.example.metropolitano12.Modelo.Recarga;
import com.example.metropolitano12.Modelo.usuarios;
import com.example.metropolitano12.R;

public class card_form extends AppCompatActivity {
    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_form);

        datos = getIntent().getExtras();
        int id = datos.getInt("id");
        String monto = datos.getString("monto");


        CardForm cardForm = findViewById(R.id.card_form);
        Button buy = findViewById(R.id.btnBuy);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(card_form.this);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cardForm.isValid()) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(card_form.this);
                    alertBuilder.setTitle("Confirm before purchase");
                    alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                            "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                            "Card CVV: " + cardForm.getCvv() + "\n" +
                            "Postal code: " + cardForm.getPostalCode() + "\n" +
                            "Phone number: " + cardForm.getMobileNumber());
                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            final BD bd = new BD(getApplicationContext());
                            UsuarioDAOImpl dao = new UsuarioDAOImpl(getApplicationContext());
                            usuarios usuario = dao.buscarPorId(id);
                            if(usuario != null){
                                double saldoFinal = usuario.getTvSaldo() + Double.parseDouble(monto);
                                dao.actualizarSaldo(id, saldoFinal);
                                Intent intent = new Intent(card_form.this, menu.class);
                                intent.putExtra("id",id);
                                RecargaDAOImpl recargaDAO = new RecargaDAOImpl(getApplicationContext());
                                Recarga nuevaRecarga = new Recarga(id, Double.parseDouble(monto));
                                recargaDAO.registrarRecarga(nuevaRecarga);
                                startActivity(intent);
                            }
                            dialogInterface.dismiss();
                            Toast.makeText(card_form.this, "Thank you for purchase", Toast.LENGTH_LONG).show();
                        }
                    });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();

                }else {
                    Toast.makeText(card_form.this, "Por favor complete los datos", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}