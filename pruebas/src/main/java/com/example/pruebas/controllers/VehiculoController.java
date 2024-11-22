package com.example.pruebas.controllers;

import com.example.pruebas.dtos.VehiculoDTO;
import com.example.pruebas.dtos.detallesDto.DetalleVehiculoDTO;
import com.example.pruebas.services.implementations.VehiculoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    final private VehiculoServiceImpl vehiculoService;


    public VehiculoController(VehiculoServiceImpl vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @PostMapping("/nuevo/vehiculo/{idModelo}/modelo")
    public ResponseEntity<String> nuevoVehiculo(@RequestBody VehiculoDTO vehiculo, @PathVariable int idModelo) {
        log.info("Agregando vehiculo");
        try {
            vehiculoService.nuevoVehiculo(vehiculo, idModelo);
            log.info("Vehiculo agregado con exito");
            return ResponseEntity.ok().body("SE AGREGO NUEVO VEHICULO");
        } catch (Exception e) {
            log.error("Error al agregar vehiculo {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("buscar/{id}")
    public ResponseEntity<DetalleVehiculoDTO> buscarVehiculo(@PathVariable int id) {
        log.info("Buscando vehiculo id: {}", id);
        try {
            DetalleVehiculoDTO vehiculo = vehiculoService.obtenerDetalleVehiculo(id);
            log.info("Vehiculo encontrado: {}", vehiculo);
            return ResponseEntity.ok().body(vehiculo);
        } catch (Exception e) {
            log.error("Error al obtener un vehiculo {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/vehiculo/actualizar")
    public ResponseEntity<String> actualizarVehiculo(@PathVariable int id, @RequestBody VehiculoDTO vehiculo) {
        log.info("Actualizando vehiculo id: {}", id);
        try {
            vehiculoService.actualizarVehiculo(id, vehiculo);
            log.info("Vehiculo actualizado {}", id);
            return ResponseEntity.ok().body("ACTUALIZADO CON EXITO");
        } catch (Exception e) {
            log.error("Error al actualizar vehiculo {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("ERROR AL ACTUALIZAR VEHICULO CON ID " + id);
        }
    }

    @DeleteMapping("/{id}/vehiculo/eliminar")
    public ResponseEntity<String> eliminarVehiculo(@PathVariable int id) {
        log.info("Eliminando vehiculo id: {}", id);
        try {
            vehiculoService.delete(id);
            log.info("Vehiculo eliminado {}", id);
            return ResponseEntity.ok().body("ELIMINADO CON EXITO");
        } catch (Exception e) {
            log.error("Error al eliminar vehiculo {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<DetalleVehiculoDTO>> getVehiculo() {
        log.info("Buscando todos los vehiculos");
        try {
            List<DetalleVehiculoDTO> vehiculos = vehiculoService.vehiculosAll();
            log.info("Encontrados {} vehiculos", vehiculos.size());
            return ResponseEntity.ok().body(vehiculos);
        } catch (Exception e) {
            log.error("Error al obtener un vehiculo {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

}