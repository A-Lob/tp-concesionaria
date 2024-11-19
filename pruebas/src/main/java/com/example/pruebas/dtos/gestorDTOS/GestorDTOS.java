package com.example.pruebas.dtos.gestorDTOS;

import com.example.pruebas.dtos.*;
import com.example.pruebas.dtos.detallesDto.DetalleVehiculoDTO;
import com.example.pruebas.models.*;
import com.example.pruebas.repositories.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private final InteresadoRepository interesadoRepository;
    private final PromocionRepository promocionRepository;

//EL GESTOR  DEVUELVE DTOS PRIMITIVOS Y LISTAS DE ESOS DTOS EN BASE A ALGO....
    @Autowired
    public GestorDTOS(VehiculoRepository vehiculoRepository,
                      PruebaRepository pruebaRepository,
                      PosicionRepository posicionRepository,
                      ModeloRepository modeloRepository,
                      MarcaRepository marcaRepository,
                      EmpleadoRepository empleadoRepository,
                      InteresadoRepository interesadoRepository,
                      PromocionRepository promocionRepository) {

        this.vehiculoRepository = vehiculoRepository;
        this.pruebaRepository = pruebaRepository;
        this.posicionRepository = posicionRepository;
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
        this.empleadoRepository = empleadoRepository;
        this.interesadoRepository = interesadoRepository;
        this.promocionRepository = promocionRepository;
    }

    public InteresadoDTO interesadoDTO(Interesado interesado){
        InteresadoDTO interesadoDTO = new InteresadoDTO();
        interesadoDTO.setTipoDocumento(interesado.getTipoDocumento());
        interesadoDTO.setNumDocumento(Integer.parseInt(interesado.getDocumento()));
        interesadoDTO.setNombre(interesado.getNombre());
        interesadoDTO.setApellido(interesado.getApellido());
        interesadoDTO.setFechaVencimientoLicencia(interesado.getFechaVencimientoLicencia());
        interesadoDTO.setNumeroLicencia(interesado.getNumeroLicencia());
        interesadoDTO.setRestringido(interesado.isRestringido());
        interesadoDTO.setEmail(interesado.getEmail());
        return interesadoDTO;
    }
    public VehiculoDTO vehiculoDTO(Vehiculo vehiculo){
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setPatente(vehiculo.getPatente());
        vehiculoDTO.setAnio(vehiculo.getAnio());
        return vehiculoDTO;
    }
    public EmpleadoDTO empleadoDTO(Empleado empleado){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setApellido(empleado.getApellido());
        empleadoDTO.setEmail(empleado.getEmail());
        empleadoDTO.setLegajo(empleado.getLegajo());
        return empleadoDTO;
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

    public List<PruebaDTO> listarPruebas(Interesado interesado) {
        List<Prueba> pruebas = interesado.getPruebas();
        return pruebas.stream().map( p -> {
                    PruebaDTO pruebaDTO = new PruebaDTO();
                    pruebaDTO.setLegajoEmpleado(p.getEmpleado().getLegajo());
                    pruebaDTO.setIdInteresado(p.getInteresado().getId());
                    pruebaDTO.setIdVehiculo(p.getVehiculo().getId());
                    return pruebaDTO;
                }

        ).toList();
    }

    public List<InteresadoDTO> listarInteresados(Promocion promocion){
        List<Interesado> interesados = promocion.getInteresados();
        return interesados.stream().map( p -> {
            InteresadoDTO interesadoDTO = new InteresadoDTO();
            interesadoDTO.setApellido(p.getApellido());
            interesadoDTO.setNombre(p.getNombre());
            interesadoDTO.setEmail(p.getEmail());
            return interesadoDTO;
        }).toList();
    }

    public List<DetalleVehiculoDTO> listarVehiculosPromocion(Promocion promocion) {
        List<Vehiculo> vehiculos = promocion.getVehiculos();
        return vehiculos.stream().map( p -> {
            VehiculoDTO vehiculoDTO = new VehiculoDTO();
            ModeloDTO modeloDTO = new ModeloDTO();
            DetalleVehiculoDTO detalleVehiculoDTO = new DetalleVehiculoDTO();

            vehiculoDTO.setAnio(p.getAnio());
            vehiculoDTO.setPatente(p.getPatente());
            modeloDTO.setDescripcion(p.getModelo().getDescripcion());
            detalleVehiculoDTO.setVehiculo(vehiculoDTO);
            detalleVehiculoDTO.setModelo(modeloDTO);

            return detalleVehiculoDTO;
        }).toList();
    }




}
