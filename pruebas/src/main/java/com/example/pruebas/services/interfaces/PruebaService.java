package com.example.pruebas.services.interfaces;

import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.models.Prueba;

public interface PruebaService extends Service<Prueba, Integer> {

    void controlarVehiculo(PosicionDTO posicion);

}
