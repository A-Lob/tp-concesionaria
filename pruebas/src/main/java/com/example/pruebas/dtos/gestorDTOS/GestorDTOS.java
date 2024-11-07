package com.example.pruebas.dtos.gestorDTOS;

import com.example.pruebas.dtos.VehiculoDTO;
import com.example.pruebas.models.Modelo;
import com.example.pruebas.models.Prueba;
import com.example.pruebas.models.Vehiculo;
import com.example.pruebas.repositories.*;
import com.example.pruebas.services.implementations.VehiculoServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public  class GestorDTOS {
    private final VehiculoRepository vehiculoRepository;
    private final PruebaRepository pruebaRepository;
    private final PosicionRepository posicionRepository;
    private final ModeloRepository modeloRepository;


    @Autowired
    public GestorDTOS(VehiculoRepository vehiculoRepository, PruebaRepository pruebaRepository,
                      PosicionRepository posicionRepository,
                      ModeloRepository modeloRepository) {

        this.vehiculoRepository = vehiculoRepository;
        this.pruebaRepository = pruebaRepository;
        this.posicionRepository = posicionRepository;
        this.modeloRepository = modeloRepository;
    }

    // se lista los vehiculos segun el argumento osea la lista de vehicolos que tiene
    public List<VehiculoDTO> listaVehiculosDtos(Modelo modelos) {
        List<Vehiculo> vehiculos = modelos.getVehiculos();
        return vehiculos.stream().map(p -> {
            VehiculoDTO vehiculoDTO = new VehiculoDTO();
            vehiculoDTO.setPatente(p.getPatente());
            vehiculoDTO.setAnio(p.getAnio());
            return vehiculoDTO;

        }).toList();
    }

    public VehiculoRepository getVehiculoRepository() {
        return vehiculoRepository;
    }

    public PruebaRepository getPruebaRepository() {
        return pruebaRepository;
    }

    public PosicionRepository getPosicionRepository() {
        return posicionRepository;
    }

    public ModeloRepository getModeloRepository() {
        return modeloRepository;
    }
}
