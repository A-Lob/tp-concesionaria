package com.example.geolocalizacion.contollers;

import com.example.geolocalizacion.entidades.Agencia;
import com.example.geolocalizacion.servicios.GeolocalizacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("/api/agencia")
public class AgenciaController {
    private GeolocalizacionServicio geolocalizacionServicion;

    public AgenciaController(GeolocalizacionServicio geolocalizacionServicion) {
        this.geolocalizacionServicion = geolocalizacionServicion;
    }

    @GetMapping("/datos")
    public ResponseEntity<Agencia> datos(){
     return ResponseEntity.ok().body(   geolocalizacionServicion.obtenerDatosAgencia());

    }
}
