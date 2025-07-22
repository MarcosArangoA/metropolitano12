package com.example.metropolitano12.Utils;

import android.util.Log;

import java.security.MessageDigest;
import java.util.Random;

public class Seguridad {

    /**
     * Genera el hash SHA-256 de la cadena de entrada.
     * @param input Texto plano a cifrar.
     * @return Hash hex de 64 caracteres.
     */
    public static String hashSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            Log.d("Seguridad", "Hash generado para '" + input + "': " + sb);
            return sb.toString();
        } catch (Exception e) {
            Log.e("Seguridad", "Error al generar hash SHA-256", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Genera un PIN de 6 dígitos aleatorio, usado como segundo factor.
     * @return PIN numérico de longitud 6.
     */
    public static String generarCodigo2FA() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        // Garantiza que el resultado esté entre 100000 y 999999
        Log.d("Seguridad", "PIN 2FA generado: " + codigo);
        return String.valueOf(codigo);
    }
}


