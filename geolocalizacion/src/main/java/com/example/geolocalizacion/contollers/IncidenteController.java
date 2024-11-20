package com.example.geolocalizacion.contollers;

import com.example.geolocalizacion.models.DetallePrueba;
import com.example.geolocalizacion.services.IncidentesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/geolocalizacion/incidentes")
public class IncidenteController {
    private IncidentesService incidentesService;
    public IncidenteController(IncidentesService incidentesService) {
            this.incidentesService = incidentesService;
    }

    @GetMapping("/pruebas")
    public ResponseEntity<List<DetallePrueba>> getPruebas() {
        try{
            List<DetallePrueba> pruebas = incidentesService.incidentes();
            return ResponseEntity.ok().body(pruebas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
