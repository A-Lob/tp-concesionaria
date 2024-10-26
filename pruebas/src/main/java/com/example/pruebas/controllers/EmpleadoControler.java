package com.example.pruebas.controllers;

import com.example.pruebas.models.Empleado;
import com.example.pruebas.services.implementations.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pruebas/empleados")
public class EmpleadoControler {
    private EmpleadoServicio empleado;

    @Autowired
    public EmpleadoControler(EmpleadoServicio empleado) {
        this.empleado = empleado;
    }

    @GetMapping("/funciona")
    public ResponseEntity<String> funciona(){
        return ResponseEntity.ok("Funciona");
    }
    @GetMapping("/id")
    public Optional<Empleado> empleado(@RequestParam("LEGAJO") long id){
        return empleado.idBuscar(id);
    }
    @GetMapping("/listar")
    public List<Empleado> empleados(){
        return empleado.listar();
    }
}
