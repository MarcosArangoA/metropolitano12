package com.example.metropolitano12.Controlador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "metropolitano.db";
    private static final int VERSION_BD = 1;

    public BD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE USUARIOS (" +
                        "IDUSUARIO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "DNI TEXT, NOMBRE TEXT, APELLIDO TEXT, " +
                        "CONTRASEÑA TEXT, CORREO TEXT, SALDO DOUBLE, PIN TEXT)"
        );
        db.execSQL("CREATE TABLE RECARGAS (IDRECARGA INTEGER PRIMARY KEY AUTOINCREMENT, IDUSUARIO INTEGER NOT NULL, MONTO REAL NOT NULL, FOREIGN KEY(IDUSUARIO) REFERENCES USUARIOS(IDUSUARIO))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS USUARIOS");
        db.execSQL("DROP TABLE IF EXISTS RECARGAS");
        onCreate(db);
    }
}
