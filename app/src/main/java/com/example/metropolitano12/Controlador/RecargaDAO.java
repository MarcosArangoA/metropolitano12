package com.example.metropolitano12.Controlador;

import com.example.metropolitano12.Modelo.Recarga;
import java.util.List;

public interface RecargaDAO {
    boolean registrarRecarga(Recarga recarga);
    List<Recarga> obtenerRecargasPorUsuario(int idUsuario);
}
