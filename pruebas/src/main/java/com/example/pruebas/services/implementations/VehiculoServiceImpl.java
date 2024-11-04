package com.example.pruebas.services.implementations;

import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.dtos.PruebaDTO;
import com.example.pruebas.dtos.VehiculoDTO;
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

@Service
public class VehiculoServiceImpl extends ServiceImpl<Vehiculo, Integer> implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final PruebaRepository pruebaRepository;
    private final PosicionRepository posicionRepository;
    private final ModeloRepository modeloRepository;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, PruebaRepository pruebaRepository, PosicionRepository posicionRepository, ModeloRepository modeloRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.pruebaRepository = pruebaRepository;
        this.posicionRepository = posicionRepository;
        this.modeloRepository = modeloRepository;
    }

    @Override
    public void add(Vehiculo vehiculo) {
        this.vehiculoRepository.save(vehiculo);
    }

    @Override
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

    public List<DetalleVehiculoDTO> findAllD() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        List<Prueba> pruebas = pruebaRepository.findAll();
        List<PruebaDTO> pruebasDtos = pruebas.stream().map(p -> {
            PruebaDTO dto = new PruebaDTO();
            dto.setIdInteresado(p.getInteresado().getId());
            dto.setIdVehiculo(p.getVehiculo().getId());
            dto.setLegajoEmpleado(p.getEmpleado().getLegajo());
            return dto;
        }).toList();

        List<Posicion> posicion = posicionRepository.findAll();
        List<PosicionDTO> posicionDtos = posicion.stream().map(pos -> {
            PosicionDTO dto = new PosicionDTO();
            dto.setIdVehiculo(pos.getVehiculo().getId());
            dto.setLatitud(pos.getLatitud());
            dto.setLongitud(pos.getLongitud());
            return dto;
        }).toList();

       List<DetalleVehiculoDTO> detalleVehiculoDTO = vehiculos.stream().map(e->{
            DetalleVehiculoDTO dto = new DetalleVehiculoDTO();
            VehiculoDTO vehiculoDTO = new VehiculoDTO();

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
    @Override

    public List<Vehiculo> findAll(){
        return this.vehiculoRepository.findAll();
    }

}
