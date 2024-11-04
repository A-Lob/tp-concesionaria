package com.example.pruebas.controllers;

import com.example.pruebas.dtos.MarcaDTO;
import com.example.pruebas.models.Marca;
import com.example.pruebas.services.interfaces.MarcaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/marca")
public class MarcaController {
    private MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

}
