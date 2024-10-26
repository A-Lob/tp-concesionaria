package com.example.pruebas.controllers;

import com.example.pruebas.models.Empleado;
import com.example.pruebas.services.interfaces.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pruebas/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Empleado>> listar() {
        List<Empleado> empleados = empleadoService.findAll();
        return ResponseEntity.ok(empleados);
    }
}
