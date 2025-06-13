package com.example.metropolitano12.Modelo;

import java.io.Serializable;

public class usuarios implements Serializable {

    private int idUsuario;
    private String tvDni;
    private String tvNombre;
    private String tvApellido;
    private String tvContraseña;
    private String tvCorreo;
    private double tvSaldo;

    public usuarios(){
    }

    public usuarios(String tvDni, String tvNombre, String tvApellido, String tvContraseña, String tvCorreo, double tvSaldo) {
        this.tvDni = tvDni;
        this.tvNombre = tvNombre;
        this.tvApellido = tvApellido;
        this.tvContraseña = tvContraseña;
        this.tvCorreo = tvCorreo;
        this.tvSaldo = tvSaldo;

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTvDni() {
        return tvDni;
    }

    public void setTvDni(String tvDni) {
        this.tvDni = tvDni;
    }

    public String getTvNombre() {
        return tvNombre;
    }

    public void setTvNombre(String tvNombre) {
        this.tvNombre = tvNombre;
    }

    public String getTvApellido() {
        return tvApellido;
    }

    public void setTvApellido(String tvApellido) {
        this.tvApellido = tvApellido;
    }

    public String getTvContraseña() {
        return tvContraseña;
    }

    public void setTvContraseña(String tvContraseña) {
        this.tvContraseña = tvContraseña;
    }

    public String getTvCorreo() {
        return tvCorreo;
    }

    public void setTvCorreo(String tvCorreo) {
        this.tvCorreo = tvCorreo;
    }

    public double getTvSaldo() {
        return tvSaldo;
    }

    public void setTvSaldo(double tvSaldo) {
        this.tvSaldo = tvSaldo;
    }

}
