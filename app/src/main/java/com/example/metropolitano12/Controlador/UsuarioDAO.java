package com.example.metropolitano12.Controlador;

import com.example.metropolitano12.Modelo.usuarios;

public interface UsuarioDAO {
    boolean registrarUsuario(usuarios usuario);
    usuarios buscarPorDni(String dni);
    usuarios buscarPorId(int id);
    boolean login(String dni, String contraseña);
    boolean actualizarSaldo(int idUsuario, double nuevoSaldo);
}
