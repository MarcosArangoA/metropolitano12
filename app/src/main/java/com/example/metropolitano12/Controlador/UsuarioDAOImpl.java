package com.example.metropolitano12.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.metropolitano12.Modelo.usuarios;

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
        values.put("CONTRASEÑA", usuario.getTvContraseña());
        values.put("CORREO", usuario.getTvCorreo());
        values.put("SALDO", usuario.getTvSaldo());

        long resultado = db.insert("USUARIOS", null, values);
        db.close();
        return resultado != -1;
    }

    @Override
    public usuarios buscarPorDni(String dni) {
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
        }
        cursor.close();
        db.close();
        return u;
    }

    @Override
    public usuarios buscarPorId(int id) {
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
        }
        cursor.close();
        db.close();
        return u;
    }

    @Override
    public boolean login(String dni, String contraseña) {
        SQLiteDatabase db = bd.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USUARIOS WHERE DNI = ? AND CONTRASEÑA = ?", new String[]{dni, contraseña});
        boolean encontrado = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return encontrado;
    }

    @Override
    public boolean actualizarSaldo(int idUsuario, double nuevoSaldo) {
        SQLiteDatabase db = bd.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SALDO", nuevoSaldo);
        int filas = db.update("USUARIOS", values, "IDUSUARIO = ?", new String[]{String.valueOf(idUsuario)});
        db.close();
        return filas > 0;
    }
}