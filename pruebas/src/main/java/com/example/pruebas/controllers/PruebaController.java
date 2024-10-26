package com.example.pruebas.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pruebas/prueba")
public class PruebaController {

    @GetMapping("/saludo")
    public ResponseEntity<String> saludo() {
        return ResponseEntity.ok("Hola");
    }

}
