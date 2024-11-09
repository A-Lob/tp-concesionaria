package com.example.pruebas.dtos.gestorDTOS;

import com.example.pruebas.dtos.*;
import com.example.pruebas.models.*;
import com.example.pruebas.repositories.*;
import com.example.pruebas.services.implementations.VehiculoServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Getter

@Component
public class GestorDTOS {
    private final VehiculoRepository vehiculoRepository;
    private final PruebaRepository pruebaRepository;
    private final PosicionRepository posicionRepository;
    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;
    private final EmpleadoRepository empleadoRepository;


    @Autowired
    public GestorDTOS(VehiculoRepository vehiculoRepository, PruebaRepository pruebaRepository,
                      PosicionRepository posicionRepository,
                      ModeloRepository modeloRepository,
                      MarcaRepository marcaRepository,
                      EmpleadoRepository empleadoRepository) {

        this.vehiculoRepository = vehiculoRepository;
        this.pruebaRepository = pruebaRepository;
        this.posicionRepository = posicionRepository;
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
        this.empleadoRepository = empleadoRepository;
    }

    // Se listan todos los vehiculos de un modelo
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

    public List<PosicionDTO> listarPosiciones(int idVehiculo) {
        List<Posicion> posiciones = vehiculoRepository.findById(idVehiculo).getPosiciones();
        return posiciones.stream().map(p -> {

                PosicionDTO posicionDTO = new PosicionDTO();
                posicionDTO.setLatitud(p.getLatitud());
                posicionDTO.setLongitud(p.getLongitud());
                posicionDTO.setIdVehiculo(idVehiculo);
                return posicionDTO;


        }).toList();
    }

    public List<PruebaDTO> listarPruebas(Empleado empleado) {
        List<Prueba> pruebas = empleado.getPruebas();
        return pruebas.stream().map( p -> {
            PruebaDTO pruebaDTO = new PruebaDTO();
            pruebaDTO.setLegajoEmpleado(p.getEmpleado().getLegajo());
            pruebaDTO.setIdInteresado(p.getInteresado().getId());
            pruebaDTO.setIdVehiculo(p.getVehiculo().getId());
            return pruebaDTO;
                }

        ).toList();
    }

    public VehiculoRepository getVehiculoRepository() {
        return vehiculoRepository;
    }

}
