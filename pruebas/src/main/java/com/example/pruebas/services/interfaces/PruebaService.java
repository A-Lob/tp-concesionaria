package com.example.pruebas.services.interfaces;

import com.example.pruebas.models.Empleado;
import com.example.pruebas.models.Interesado;
import com.example.pruebas.models.Prueba;
import com.example.pruebas.models.Vehiculo;

public interface PruebaService extends Service<Prueba, Integer> {

    Interesado AssignInteresadoToPrueba(int id);
    Vehiculo AssignVehiculoToPrueba(int id);
    Empleado AssignEmpleadoToPrueba(int id);

}
