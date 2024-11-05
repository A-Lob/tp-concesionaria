package com.example.pruebas.controllers;

import com.example.pruebas.dtos.detallesDto.DetalleVehiculoDTO;
import com.example.pruebas.services.implementations.VehiculoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        try{
            DetalleVehiculoDTO  vehiculo = vehiculoService.obtenerDetalleVehiculo(id);
            return ResponseEntity.ok().body(vehiculo);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/todos")
    public ResponseEntity<List<DetalleVehiculoDTO>> getVehiculo() {
        try{
            List<DetalleVehiculoDTO> lista = vehiculoService.obtenerDetallesVehiculos();
            return ResponseEntity.ok().body(lista);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }

    }


}
