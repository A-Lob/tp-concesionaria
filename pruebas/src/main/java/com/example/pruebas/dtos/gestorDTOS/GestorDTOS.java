package com.example.pruebas.dtos.gestorDTOS;

import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.dtos.VehiculoDTO;
import com.example.pruebas.models.Marca;
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
    private final MarcaRepository marcaRepository;


    @Autowired
    public GestorDTOS(VehiculoRepository vehiculoRepository, PruebaRepository pruebaRepository,
                      PosicionRepository posicionRepository,
                      ModeloRepository modeloRepository,
                      MarcaRepository marcaRepository) {

        this.vehiculoRepository = vehiculoRepository;
        this.pruebaRepository = pruebaRepository;
        this.posicionRepository = posicionRepository;
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
    }

    // se lista los vehiculos segun el argumento osea la lista de vehicolos que tiene
    public List<VehiculoDTO> listaVehiculosDtos(Modelo modelo) {
        List<Vehiculo> vehiculos = modelo.getVehiculos();
        return vehiculos.stream().map(p -> {
            VehiculoDTO vehiculoDTO = new VehiculoDTO();
            vehiculoDTO.setPatente(p.getPatente());
            vehiculoDTO.setAnio(p.getAnio());
            return vehiculoDTO;

        }).toList();
    }
    public List<ModeloDTO> listarModelos(Marca marca) {
        List<Modelo> modelos = marca.getModelos();
        return modelos.stream().map(m -> {
            ModeloDTO modeloDTO = new ModeloDTO();
            modeloDTO.setDescripcion(m.getDescripcion());

            return modeloDTO;

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

    public MarcaRepository getMarcaRepository() {
        return marcaRepository;
    }
}
