package com.example.pruebas.services.interfaces;

import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.dtos.PruebaDTO;
import com.example.pruebas.models.Prueba;

import java.time.LocalDateTime;
import java.util.List;

public interface PruebaService {

    void add (PruebaDTO prueba);
    void update (Prueba prueba);
    List<Prueba> findPruebasByFechaHora(LocalDateTime fechaHora);
    Prueba findPruebaFin(int id);
    void controlarVehiculo(PosicionDTO posicion);

}
