package com.example.pruebas.services.implementations;

import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.dtos.VehiculoDTO;
import com.example.pruebas.dtos.detallesDto.DetallePosicionDTO;
import com.example.pruebas.dtos.gestorDTOS.GestorDTOS;
import com.example.pruebas.models.Posicion;

import com.example.pruebas.models.Vehiculo;
import com.example.pruebas.services.interfaces.PosicionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PosicionServiceImpl extends ServiceImpl<Posicion, Integer> implements PosicionService {
    private final GestorDTOS gestorDTOS;

    public PosicionServiceImpl(GestorDTOS gestorDTOS) {
        this.gestorDTOS = gestorDTOS;
    }

    @Override
    public void add(Posicion posicion) {
        gestorDTOS.getPosicionRepository().save(posicion);


    }

    @Override
    public void update(Posicion posicion) {
        gestorDTOS.getPosicionRepository().save(posicion);

    }

    @Override
    public void delete(Integer id) {
        gestorDTOS.getPosicionRepository().deleteById(id);
    }

    @Override
    public Posicion findById(Integer id) {
        return gestorDTOS.getPosicionRepository().findById(id).orElseThrow();
    }

    @Override
    public List<Posicion> findAll() {
        return gestorDTOS.getPosicionRepository().findAll();
    }

    public List<DetallePosicionDTO> posicionesAll() {
        log.info("Listando detalles de todas las posiciones");
        List<Posicion> posiciones = findAll();
        List<DetallePosicionDTO> detallePosicionDTOS = posiciones.stream().map(p -> {
            DetallePosicionDTO detallePosicionDTO  = new DetallePosicionDTO();
            detallePosicionDTO.setPosicion(gestorDTOS.posicionDTO(p));
            detallePosicionDTO.setVehiculo(gestorDTOS.vehiculoDTO(p.getVehiculo()));



            return detallePosicionDTO;


        }).toList();
        return detallePosicionDTOS;
    }

    public List<DetallePosicionDTO> posicionesAll(double latitudMin,double longitudMin,
                                                double longitudMax, double latitudMax) {
        log.info("Listando detalles de todas las posiciones dentro de un area");
        List<DetallePosicionDTO> detallePosicionDTOS = posicionesAll();
        List<DetallePosicionDTO> posiciones = detallePosicionDTOS.stream().filter(p -> {
            if (p.getPosicion() != null) {
                double lati = p.getPosicion().getLatitud();
                double longi = p.getPosicion().getLongitud();
                // Puedes usar lati y longi aquí
                if ((lati <= latitudMax && longi <= longitudMax) && (lati >= latitudMin && longi >= longitudMin) ) {
                    return true;
                }
            }
            return false;

        }).toList();
        return posiciones;
    }

    public void agregar(PosicionDTO posicionDTO) {
        Posicion posicion = new Posicion();
        Vehiculo vehiculo = gestorDTOS.getVehiculoRepository().findById(posicionDTO.getIdVehiculo());
        posicion.setVehiculo(vehiculo);
        posicion.setLongitud(posicionDTO.getLongitud());
        posicion.setLatitud(posicionDTO.getLatitud());

        add(posicion);

    }

    public void eliminar(int id) {
        delete(id);
    }


}
