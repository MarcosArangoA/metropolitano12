package com.example.metropolitano12.Modelo;

public class Recarga {
    private int idRecarga;
    private int idUsuario;
    private double monto;

    public Recarga() {}

    public Recarga(int idUsuario, double monto) {
        this.idUsuario = idUsuario;
        this.monto = monto;
    }

    public int getIdRecarga() {
        return idRecarga;
    }

    public void setIdRecarga(int idRecarga) {
        this.idRecarga = idRecarga;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
