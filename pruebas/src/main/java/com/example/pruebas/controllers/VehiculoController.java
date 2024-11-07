package com.example.pruebas.controllers;

import com.example.pruebas.dtos.VehiculoDTO;
import com.example.pruebas.dtos.detallesDto.DetalleVehiculoDTO;
import com.example.pruebas.services.implementations.VehiculoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    final private VehiculoServiceImpl vehiculoService;


    public VehiculoController(VehiculoServiceImpl vehiculoService) {
        this.vehiculoService = vehiculoService;

    }

    @GetMapping("buscar/{id}")
    public ResponseEntity<DetalleVehiculoDTO> buscarVehiculo(@PathVariable int id) {
        try {
            DetalleVehiculoDTO vehiculo = vehiculoService.obtenerDetalleVehiculo(id);
            return ResponseEntity.ok().body(vehiculo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<DetalleVehiculoDTO>> getVehiculo() {
        try {
            List<DetalleVehiculoDTO> lista = vehiculoService.obtenerDetallesVehiculos();
            return ResponseEntity.ok().body(lista);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();

        }

    }

    @PutMapping("/{id}/vehiculo/actualizar")
    public ResponseEntity<String> actualizarVehiculo(@PathVariable int id, @RequestBody VehiculoDTO vehiculo) {
        try {
            vehiculoService.actualizarVehiculo(id, vehiculo);
            return ResponseEntity.ok().body("ACTUALIZADO CON EXITO");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR AL ACTUALIZAR VEHICULO CON ID " + id);
        }
    }
 @DeleteMapping("/{id}/vehiculo/eliminar")
 public ResponseEntity<String> eliminarVehiculo(@PathVariable int id) {
        try{
            vehiculoService.delete(id);
            return ResponseEntity.ok().body("ELIMINADO CON EXITO");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
 }
}