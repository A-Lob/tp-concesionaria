package com.example.pruebas.services.interfaces;

import com.example.pruebas.models.Empleado;
import com.example.pruebas.models.Interesado;
import com.example.pruebas.models.Prueba;
import com.example.pruebas.models.Vehiculo;

import java.time.LocalDateTime;
import java.util.List;

public interface PruebaService extends Service<Prueba, Integer> {

    Interesado AssignInteresadoToPrueba(int id);
    Vehiculo AssignVehiculoToPrueba(int id);
    Empleado AssignEmpleadoToPrueba(int id);
    List<Prueba> findPruebasByFechaHora(LocalDateTime fechaHora);
    Prueba findPruebaFin(int id);

}
