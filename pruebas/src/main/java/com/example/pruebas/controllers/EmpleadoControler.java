package com.example.pruebas.controllers;

import com.example.pruebas.models.Empleado;
import com.example.pruebas.servicios.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Empleado")
public class EmpleadoControler {
    private EmpleadoServicio empleado;

    @Autowired
    public EmpleadoControler(EmpleadoServicio empleado) {
        this.empleado = empleado;
    }

    @GetMapping("/funciona")
    public String funciona(){
        return "Funciona";
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
