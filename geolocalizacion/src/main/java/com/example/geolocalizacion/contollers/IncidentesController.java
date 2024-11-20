package com.example.geolocalizacion.contollers;

import com.example.geolocalizacion.models.DetallePrueba;
import com.example.geolocalizacion.services.PruebaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/geolocalizacion/incidentes")
public class IncidentesController {
    private PruebaService pruebaService;

    public IncidentesController(PruebaService pruebaService) {
        this.pruebaService = pruebaService;
    }

    @GetMapping("/pruebas")
    public ResponseEntity<List<DetallePrueba>> pruebas() {
        return ResponseEntity.ok(pruebaService.obtenerDatosPrueba());
    }
}
