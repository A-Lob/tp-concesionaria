package com.example.geolocalizacion.contollers;

import com.example.geolocalizacion.models.DetalleIncidente;
import com.example.geolocalizacion.models.DetallePrueba;
import com.example.geolocalizacion.services.ReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/geolocalizacion/reportes")
public class ReportesController {
    private ReporteService reporteService;
    public ReportesController(ReporteService reporteService) {
            this.reporteService = reporteService;
    }

    @GetMapping("/indicente/pruebas")
    public ResponseEntity<List<DetallePrueba>> getPruebas() {
        try{
            List<DetallePrueba> pruebas = reporteService.incidentes();
            return ResponseEntity.ok().body(pruebas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/indicente/{legajo}/empleado")
    public ResponseEntity<DetalleIncidente> getEmpleado(@PathVariable int legajo) {
        try{
            DetalleIncidente incidentes = reporteService.detalleIncidenteEmpleado(legajo);
            return ResponseEntity.ok().body(incidentes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/indicente/{id}/vehiculo")
    public ResponseEntity<DetalleIncidente> getVehiculo(@PathVariable int id) {
        try{
            DetalleIncidente incidentes = reporteService.detalleIncidenteVehiculo(id);
            return ResponseEntity.ok().body(incidentes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
