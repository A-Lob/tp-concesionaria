package com.example.pruebas.controllers;

import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.dtos.PruebaDTO;
import com.example.pruebas.dtos.VehiculoDTO;
import com.example.pruebas.dtos.detallesDto.DetalleVehiculoDTO;
import com.example.pruebas.models.Vehiculo;
import com.example.pruebas.services.implementations.VehiculoServiceImpl;
import com.example.pruebas.services.interfaces.VehiculoService;
import jakarta.servlet.ServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    final private VehiculoServiceImpl vehiculoService;


    public VehiculoController(VehiculoServiceImpl vehiculoService) {
        this.vehiculoService = vehiculoService;

    }


    @GetMapping("/todos")
    public ResponseEntity<List<DetalleVehiculoDTO>> getVehiculoId(ServletRequest servletRequest) {
        try{
            List<DetalleVehiculoDTO> lista = vehiculoService.findAllD();
            return ResponseEntity.ok().body(lista);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }

    }


}
