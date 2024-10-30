package com.example.pruebas.controllers;

import com.example.pruebas.dtos.PruebaDTO;
import com.example.pruebas.models.Empleado;
import com.example.pruebas.models.Prueba;
import com.example.pruebas.services.implementations.PruebaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pruebas")
public class PruebaController {

    private final PruebaServiceImpl pruebaService;

    public PruebaController(PruebaServiceImpl pruebaService) {
        this.pruebaService = pruebaService;
    }

    @PostMapping
    public ResponseEntity<Object> crearPrueba(@RequestBody PruebaDTO prueba) {
        try {
            Prueba nuevaPrueba = new Prueba();
            nuevaPrueba.setInteresado(pruebaService.AssignInteresadoToPrueba(prueba.getIdInteresado()));
            nuevaPrueba.setVehiculo(pruebaService.AssignVehiculoToPrueba(prueba.getIdVehiculo()));
            nuevaPrueba.setEmpleado(pruebaService.AssignEmpleadoToPrueba(prueba.getLegajoEmpleado()));
            nuevaPrueba.setFechaHoraInicio(LocalDateTime.now());
            this.pruebaService.add(nuevaPrueba);
            return new ResponseEntity<>(prueba, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
