package com.example.metropolitano12.Utils;

import android.util.Log;

import java.security.MessageDigest;

public class Seguridad {

    public static String hashSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            // Log para verificar el hash generado
            Log.d("Seguridad", "Hash generado para input: " + input + " => " + sb.toString());

            return sb.toString();
        } catch (Exception e) {
            Log.e("Seguridad", "Error al generar hash", e);
            throw new RuntimeException(e);
        }
    }
}

