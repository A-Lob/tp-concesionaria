package com.example.pruebas.controllers;

import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.dtos.detallesDto.DetalleModeloDTO;
import com.example.pruebas.models.Modelo;
import com.example.pruebas.services.implementations.ModeloServiceImpl;
import com.example.pruebas.services.interfaces.ModeloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/modelo")
public class ModeloController {
    private ModeloServiceImpl modeloServiceImpl;


    public ModeloController(ModeloServiceImpl modeloServiceImpl) {
        this.modeloServiceImpl = modeloServiceImpl;
    }

    @GetMapping("/todos/modelos")
    public ResponseEntity<List<DetalleModeloDTO>> getAllModelos() {
        try{
            return ResponseEntity.ok().body(modeloServiceImpl.modelosAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
