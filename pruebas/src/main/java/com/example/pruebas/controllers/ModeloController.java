package com.example.pruebas.controllers;

import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.models.Modelo;
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
    private ModeloService modeloService;


    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;

    }


}
