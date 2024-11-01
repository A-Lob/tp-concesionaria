package com.example.pruebas.controllers;

import com.example.pruebas.dtos.*;
import com.example.pruebas.models.Prueba;
import com.example.pruebas.models.Vehiculo;
import com.example.pruebas.services.implementations.PruebaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/pruebas")
public class PruebaController {

    private final PruebaServiceImpl pruebaService;

    public PruebaController(PruebaServiceImpl pruebaService) {
        this.pruebaService = pruebaService;
    }

    // 1.a) Crear una nueva prueba, haciendo las validaciones correspondientes
    @PostMapping("/nueva-prueba")
    public ResponseEntity<Object> crearPrueba(@RequestBody PruebaDTO solicitudPrueba) {
        try {
            // Se envian los datos a la bd y se realizan las validaciones correspondientes
            this.pruebaService.add(solicitudPrueba);
            return new ResponseEntity<>(solicitudPrueba, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 1.b) Listar pruebas en curso en un momento dado
    // Se envia la fecha-hora en la ruta
    @GetMapping("/listar-pruebas/{fechaHora}")
    public ResponseEntity<List<Prueba>> getPruebas(@PathVariable String fechaHora) {
        try {
            // Obtengo las pruebas activas en el momento de la bd, es decir las que la fecha de fin no
            // estan definidas.
            List<Prueba> pruebasActivas = pruebaService.findPruebasByFechaHora(LocalDateTime.parse(fechaHora, DateTimeFormatter.ISO_DATE_TIME));
            return ResponseEntity.ok(pruebasActivas);
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    // 1.c) Finalizar una prueba, permitiéndole al empleado agregar un comentario
    // sobre la misma.
    @PutMapping("/finalizar-prueba/{id}")
    public ResponseEntity<Object> finalizarPrueba(@PathVariable int id, @RequestBody FinPruebaDTO prueba) {
        try {
            // Busco la prueba por su id en la bd y no debe estar finalizada.
            Prueba pruebaLocal = this.pruebaService.findPruebaFin(id);

            // El empleado puede asignar el comentario y finaliza la prueba
            pruebaLocal.setComentario(prueba.getComentario());
            pruebaLocal.setFechaHoraFin(LocalDateTime.now());

            // Se actualiza la prueba como finalizada
            pruebaService.update(pruebaLocal);
            return new ResponseEntity<>(pruebaLocal, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/controlar-vehiculo")
    public ResponseEntity<Object> evaluarPosicion(@RequestBody PosicionDTO posicion) {
        try {
            this.pruebaService.controlarVehiculo(posicion);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/promocionar-pruebas")
    public ResponseEntity<Object> enviarPromociones(PromocionDTO promocion) {
        try {
            this.pruebaService.enviarPromociones(promocion);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    };

}
