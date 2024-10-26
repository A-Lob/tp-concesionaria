package com.example.pruebas.controllers;

import com.example.pruebas.dtos.PruebaDto;
import com.example.pruebas.models.Interesado;
import com.example.pruebas.models.Prueba;
import com.example.pruebas.models.Vehiculo;
import com.example.pruebas.services.interfaces.PruebaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pruebas/prueba")
public class PruebaController {

    private final PruebaService pruebaService;

    public PruebaController(PruebaService pruebaService) {
        this.pruebaService = pruebaService;
    }

    @PostMapping
    public ResponseEntity<Object> crearPrueba(@RequestBody PruebaDto prueba) {
        // Obtengo la fecha actual de la solicitud
        LocalDate fechaActual = LocalDate.now();

        // Busco al interesado de la prueba en la base de datos
        Interesado interesado = pruebaService.findInteresadoById(prueba.getInteresado().getId());

        // Busco si el vehiculo esta en alguna prueba
        Vehiculo vehiculo = pruebaService.findVehiculoById(prueba.getVehiculo().getId());

        // Si ya esta en una prueba tengo que ver si la prueba ya finalizo
        // Si no registra la fecha de fin de la prueba entonces todavia esta en una prueba y no se puede utilizar
        if (vehiculo != null && prueba.getFechaHoraFin() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El vehiculo esta siendo utilizado en otra prueba, no se puede generar la prueba");
        }

        // Valido que no tenga licencia vencida
        if (interesado.getFechaVencimientoLicencia().isBefore(fechaActual)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La licencia del interesado esta vencida, no se puede generar la prueba");
        }

        // Valido que no tenga restricciones
        if (interesado.isRestringido()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El interesado posee restriccion, no se puede generar la prueba");
        }

        // Si el vehiculo no se usa en una prueba, el interesado no tiene licencia vencida y no hay restricciones
        // entonces puedo crear una nueva prueba

        try {
            Prueba nuevaPrueba = new Prueba();
            nuevaPrueba.setInteresado(interesado);
            nuevaPrueba.setVehiculo(vehiculo);
            nuevaPrueba.setFechaHoraInicio(LocalDateTime.now());
            return new ResponseEntity<>(prueba, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
