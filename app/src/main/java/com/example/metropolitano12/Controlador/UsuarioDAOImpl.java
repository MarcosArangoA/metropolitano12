package com.example.metropolitano12.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.metropolitano12.Modelo.usuarios;
import com.example.metropolitano12.Utils.Seguridad;

public class UsuarioDAOImpl implements UsuarioDAO {

    private BD bd;

    public UsuarioDAOImpl(Context context) {
        bd = new BD(context);
    }

    @Override
    public boolean registrarUsuario(usuarios usuario) {
        SQLiteDatabase db = bd.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DNI", usuario.getTvDni());
        values.put("NOMBRE", usuario.getTvNombre());
        values.put("APELLIDO", usuario.getTvApellido());

        String hash = Seguridad.hashSHA256(usuario.getTvContraseña());
        values.put("CONTRASEÑA", hash);

        values.put("CORREO", usuario.getTvCorreo());
        values.put("SALDO", usuario.getTvSaldo());

        long resultado = db.insert("USUARIOS", null, values);
        db.close();

        if (resultado != -1) {
            Log.i("UsuarioDAO", "Usuario registrado exitosamente: " + usuario.getTvDni());
            return true;
        } else {
            Log.e("UsuarioDAO", "Error al registrar usuario: " + usuario.getTvDni());
            return false;
        }
    }

    @Override
    public usuarios buscarPorDni(String dni) {
        Log.d("UsuarioDAO", "Buscando usuario por DNI: " + dni);
        SQLiteDatabase db = bd.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USUARIOS WHERE DNI = ?", new String[]{dni});
        usuarios u = null;
        if (cursor.moveToFirst()) {
            u = new usuarios();
            u.setIdUsuario(cursor.getInt(0));
            u.setTvDni(cursor.getString(1));
            u.setTvNombre(cursor.getString(2));
            u.setTvApellido(cursor.getString(3));
            u.setTvContraseña(cursor.getString(4));
            u.setTvCorreo(cursor.getString(5));
            u.setTvSaldo(cursor.getDouble(6));

            Log.i("UsuarioDAO", "Usuario encontrado: " + dni);
        } else {
            Log.w("UsuarioDAO", "Usuario no encontrado: " + dni);
        }
        cursor.close();
        db.close();
        return u;
    }

    @Override
    public usuarios buscarPorId(int id) {
        Log.d("UsuarioDAO", "Buscando usuario por ID: " + id);
        SQLiteDatabase db = bd.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USUARIOS WHERE IDUSUARIO = ?", new String[]{String.valueOf(id)});
        usuarios u = null;
        if (cursor.moveToFirst()) {
            u = new usuarios();
            u.setIdUsuario(cursor.getInt(0));
            u.setTvDni(cursor.getString(1));
            u.setTvNombre(cursor.getString(2));
            u.setTvApellido(cursor.getString(3));
            u.setTvContraseña(cursor.getString(4));
            u.setTvCorreo(cursor.getString(5));
            u.setTvSaldo(cursor.getDouble(6));

            Log.i("UsuarioDAO", "Usuario encontrado por ID: " + id);
        } else {
            Log.w("UsuarioDAO", "Usuario no encontrado con ID: " + id);
        }
        cursor.close();
        db.close();
        return u;
    }

    @Override
    public boolean login(String dni, String contraseña) {
        SQLiteDatabase db = bd.getReadableDatabase();
        String hash = Seguridad.hashSHA256(contraseña);

        Log.d("UsuarioDAO", "Intento de login para DNI: " + dni);

        Cursor cursor = db.rawQuery("SELECT * FROM USUARIOS WHERE DNI = ? AND CONTRASEÑA = ?", new String[]{dni, hash});
        boolean encontrado = cursor.getCount() > 0;
        cursor.close();
        db.close();

        if (encontrado) {
            Log.i("UsuarioDAO", "Login exitoso para DNI: " + dni);
        } else {
            Log.w("UsuarioDAO", "Login fallido para DNI: " + dni);
        }
        return encontrado;
    }

    @Override
    public boolean actualizarSaldo(int idUsuario, double nuevoSaldo) {
        SQLiteDatabase db = bd.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SALDO", nuevoSaldo);

        int filas = db.update("USUARIOS", values, "IDUSUARIO = ?", new String[]{String.valueOf(idUsuario)});
        db.close();

        if (filas > 0) {
            Log.i("UsuarioDAO", "Saldo actualizado para IDUsuario: " + idUsuario + ", Nuevo saldo: " + nuevoSaldo);
            return true;
        } else {
            Log.e("UsuarioDAO", "Error al actualizar saldo para IDUsuario: " + idUsuario);
            return false;
        }
    }
}

