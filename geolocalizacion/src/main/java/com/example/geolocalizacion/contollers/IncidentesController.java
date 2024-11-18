package com.example.geolocalizacion.contollers;

import com.example.geolocalizacion.modelos.DetallePrueba;
import com.example.geolocalizacion.servicios.PruebaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/geolocalizacion/incidentes")
public class IncidentesController {
    private PruebaServicio pruebaServicio;

    public IncidentesController(PruebaServicio pruebaServicio) {
        this.pruebaServicio = pruebaServicio;
    }

    @GetMapping("/pruebas")
    public ResponseEntity<List<DetallePrueba>> pruebas() {
        return ResponseEntity.ok(pruebaServicio.obtenerDatosPrueba());
    }
}
