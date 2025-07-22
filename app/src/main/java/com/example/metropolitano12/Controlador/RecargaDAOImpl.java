package com.example.metropolitano12.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.metropolitano12.Modelo.Recarga;

import java.util.ArrayList;
import java.util.List;

public class RecargaDAOImpl implements RecargaDAO {

    private BD bd;

    public RecargaDAOImpl(Context context) {
        this.bd = new BD(context);
    }

    @Override
    public boolean registrarRecarga(Recarga recarga) {
        SQLiteDatabase db = bd.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("IDUSUARIO", recarga.getIdUsuario());
        values.put("MONTO", recarga.getMonto());

        long result = db.insert("RECARGAS", null, values);
        db.close();

        if (result != -1) {
            Log.i("RecargaDAO", "Recarga registrada: IDUsuario=" + recarga.getIdUsuario() + ", Monto=" + recarga.getMonto());
            return true;
        } else {
            Log.e("RecargaDAO", "Error al registrar recarga: IDUsuario=" + recarga.getIdUsuario());
            return false;
        }
    }

    @Override
    public List<Recarga> obtenerRecargasPorUsuario(int idUsuario) {
        Log.d("RecargaDAO", "Obteniendo recargas para IDUsuario: " + idUsuario);
        List<Recarga> lista = new ArrayList<>();
        SQLiteDatabase db = bd.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM RECARGAS WHERE IDUSUARIO = ?", new String[]{String.valueOf(idUsuario)});

        while (cursor.moveToNext()) {
            Recarga r = new Recarga();
            r.setIdRecarga(cursor.getInt(0));
            r.setIdUsuario(cursor.getInt(1));
            r.setMonto(cursor.getDouble(2));
            lista.add(r);
        }

        Log.i("RecargaDAO", "Total recargas encontradas: " + lista.size());
        cursor.close();
        db.close();
        return lista;
    }
}
