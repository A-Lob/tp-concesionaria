package com.example.pruebas.services.interfaces;

import com.example.pruebas.models.Interesado;
import com.example.pruebas.models.Prueba;
import com.example.pruebas.models.Vehiculo;

public interface PruebaService extends Service<Prueba, Integer> {
    Interesado findInteresadoById(int id);
    Vehiculo findVehiculoById(int id);
}
