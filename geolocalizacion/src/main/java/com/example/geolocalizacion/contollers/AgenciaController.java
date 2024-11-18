package com.example.geolocalizacion.contollers;

import com.example.geolocalizacion.modelos.Agencia;
import com.example.geolocalizacion.servicios.GeolocalizacionServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//ELIMINAR LUEGO DE PREUBAS
@RestController("api/geolocalizacion/agencia")
public class AgenciaController {
    private GeolocalizacionServicio geolocalizacionServicion;

    public AgenciaController(GeolocalizacionServicio geolocalizacionServicion) {
        this.geolocalizacionServicion = geolocalizacionServicion;
    }

    @GetMapping("/datos")
    public ResponseEntity<Agencia> datos(){
     return ResponseEntity.ok().body(geolocalizacionServicion.obtenerDatosAgencia());

    }
}
