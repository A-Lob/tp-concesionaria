package com.example.pruebas.services.implementations;

import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.dtos.PruebaDTO;
import com.example.pruebas.dtos.VehiculoDTO;
import com.example.pruebas.dtos.detallesDto.DetalleModeloDTO;
import com.example.pruebas.dtos.detallesDto.DetalleVehiculoDTO;
import com.example.pruebas.models.Modelo;
import com.example.pruebas.models.Posicion;
import com.example.pruebas.models.Prueba;
import com.example.pruebas.models.Vehiculo;
import com.example.pruebas.repositories.ModeloRepository;
import com.example.pruebas.repositories.PosicionRepository;
import com.example.pruebas.repositories.PruebaRepository;
import com.example.pruebas.repositories.VehiculoRepository;
import com.example.pruebas.services.interfaces.VehiculoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl extends ServiceImpl<Vehiculo, Integer> implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final PruebaRepository pruebaRepository;
    private final PosicionRepository posicionRepository;
    private final ModeloRepository modeloRepository;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, PruebaRepository pruebaRepository,
                               PosicionRepository posicionRepository, ModeloRepository modeloRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.pruebaRepository = pruebaRepository;
        this.posicionRepository = posicionRepository;
        this.modeloRepository = modeloRepository;

    }

    @Override
    public void add(Vehiculo vehiculo) {
        this.vehiculoRepository.save(vehiculo);
    }

    public void update(Vehiculo vehiculo) {
        this.vehiculoRepository.save(vehiculo);
    }

    @Override
    public void delete(Integer id) {
        Vehiculo vehiculo = this.vehiculoRepository.findById(id).orElseThrow();
        this.vehiculoRepository.delete(vehiculo);
    }

    @Override
    public Vehiculo findById(Integer id) {
        return this.vehiculoRepository.findById(id).orElseThrow();
    }

    @Override

    public List<Vehiculo> findAll() {
        return this.vehiculoRepository.findAll();
    }

    public List<DetalleVehiculoDTO> obtenerDetallesVehiculos() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        List<Prueba> pruebas = pruebaRepository.findAll();


        List<Posicion> posicion = posicionRepository.findAll();


        List<DetalleVehiculoDTO> detalleVehiculoDTO = vehiculos.stream().map(e -> {
                    DetalleVehiculoDTO dto = new DetalleVehiculoDTO();
                    VehiculoDTO vehiculoDTO = new VehiculoDTO();
                    List<PruebaDTO> pruebasDtos = this.listadoPruebasDto(pruebas, List.of(e));
                    List<PosicionDTO> posicionDtos = this.listadoPosicionesDto(posicion, List.of(e));

                    vehiculoDTO.setPatente(e.getPatente());
                    vehiculoDTO.setAnio(e.getAnio());

                    dto.setVehiculo(vehiculoDTO);
                    dto.setPruebas(pruebasDtos);
                    dto.setPosiciones(posicionDtos);
                    ModeloDTO modelo = new ModeloDTO(e.getModelo().getDescripcion());
                    dto.setModelo(modelo);
                    return dto;
                })
                .toList();


        return detalleVehiculoDTO;
    }

    public DetalleVehiculoDTO obtenerDetalleVehiculo(Integer id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElseThrow();
        DetalleVehiculoDTO dtoVehiculo = new DetalleVehiculoDTO();
        List<Prueba> pruebas = pruebaRepository.findAll();
        List<PruebaDTO> pruebasDtos = this.listadoPruebasDto(pruebas, id);
        List<Posicion> posicion = posicionRepository.findAll();
        List<PosicionDTO> posicionDtos = this.listadoPosicionesDto(posicion, id);


        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setPatente(vehiculo.getPatente());
        vehiculoDTO.setAnio(vehiculo.getAnio());

        dtoVehiculo.setPruebas(pruebasDtos);
        dtoVehiculo.setPosiciones(posicionDtos);
        dtoVehiculo.setVehiculo(vehiculoDTO);
        ModeloDTO modelo = new ModeloDTO(vehiculo.getModelo().getDescripcion());
        dtoVehiculo.setModelo(modelo);
        return dtoVehiculo;
    }

    private List<PruebaDTO> listadoPruebasDto(List<Prueba> pruebas, List<Vehiculo> vehiculos) {
        List<PruebaDTO> pruebasDtos = pruebas.stream()
                .map(p -> {
                    PruebaDTO dto = new PruebaDTO();
                    dto.setIdInteresado(p.getInteresado().getId());
                    dto.setIdVehiculo(p.getVehiculo().getId());
                    dto.setLegajoEmpleado(p.getEmpleado().getLegajo());
                    return dto;
                }).toList();
        List<PruebaDTO> pruebasDtosF = pruebasDtos.stream()
                .filter(p1 -> vehiculos.stream().anyMatch(p2 -> p2.getId() == p1.getIdVehiculo()))
                .toList();


        return pruebasDtosF;
    }

    private List<PruebaDTO> listadoPruebasDto(List<Prueba> pruebas, int id) {
        List<PruebaDTO> pruebasDtos = pruebas.stream()
                .map(p -> {
                    PruebaDTO dto = new PruebaDTO();
                    dto.setIdInteresado(p.getInteresado().getId());
                    dto.setIdVehiculo(p.getVehiculo().getId());
                    dto.setLegajoEmpleado(p.getEmpleado().getLegajo());
                    return dto;
                }).toList();

        pruebasDtos = pruebasDtos.stream().filter(p -> p.getIdVehiculo() == id).toList();

        return pruebasDtos;
    }

    private List<PosicionDTO> listadoPosicionesDto(List<Posicion> posicion, List<Vehiculo> vehiculos) {

        List<PosicionDTO> posicionDtos = posicion.stream().map(pos -> {
            PosicionDTO dto = new PosicionDTO();
            dto.setIdVehiculo(pos.getVehiculo().getId());
            dto.setLatitud(pos.getLatitud());
            dto.setLongitud(pos.getLongitud());
            return dto;
        }).toList();

        List<PosicionDTO> posicionDtosF = posicionDtos.stream()
                .filter(p1 -> vehiculos.stream().anyMatch(p2 -> p2.getId() == p1.getIdVehiculo()))
                .toList();;
        return posicionDtosF;
    }

    private List<PosicionDTO> listadoPosicionesDto(List<Posicion> posicion, int id) {

        List<PosicionDTO> posicionDtos = posicion.stream().map(pos -> {
            PosicionDTO dto = new PosicionDTO();
            dto.setIdVehiculo(pos.getVehiculo().getId());
            dto.setLatitud(pos.getLatitud());
            dto.setLongitud(pos.getLongitud());
            return dto;
        }).toList();
        posicionDtos = posicionDtos.stream().filter(e -> e.getIdVehiculo() == id).toList();
        return posicionDtos;
    }


    public void actualizarVehiculo(int id, VehiculoDTO vehiculoDTO ) {
        DetalleVehiculoDTO  detalleVehiculoDTO =  obtenerDetalleVehiculo(id);
        detalleVehiculoDTO.setVehiculo(vehiculoDTO);
        Vehiculo vehiculo = findById(id);
        vehiculo.setPatente(detalleVehiculoDTO.getVehiculo().getPatente());
        vehiculo.setAnio(detalleVehiculoDTO.getVehiculo().getAnio());

        Modelo modelo = modeloRepository.findById(id);

        update(vehiculo);


    }
}
