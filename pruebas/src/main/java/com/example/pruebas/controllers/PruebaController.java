package com.example.pruebas.controllers;

import com.example.pruebas.dtos.*;
import com.example.pruebas.dtos.detallesDto.DetallePruebaDTO;
import com.example.pruebas.services.implementations.PruebaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        log.info("Agregando prueba");
        try {
            pruebaService.agregar(solicitudPrueba);
            log.info("Prueba agregada con exito");
            return new ResponseEntity<>(solicitudPrueba, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
    // 1.b) Listar pruebas en curso en un momento dado
    // Se envia la fecha-hora en la ruta
    // formato fecha: año-mes-dia, hora: hora
    @GetMapping("/listar-pruebas/{fecha}/{hora}")
    public ResponseEntity<List<DetallePruebaDTO>> getPruebasCondicional(@PathVariable String fecha,
                                                             @PathVariable String hora) {
        log.info("Buscando todas las pruebas condicionales");
        try {
            List<DetallePruebaDTO> pruebas = pruebaService.TodosPorFechas(fecha, hora);
            log.info("Encontradas {} pruebas condicionales", pruebas.size());
            return ResponseEntity.ok().body(pruebas);
        } catch (Exception exception) {
            log.error("Error al buscar pruebas {}", exception.getMessage(), exception);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/todas")
    public ResponseEntity<List<DetallePruebaDTO>> getPruebasAll() {
        log.info("Buscando todas las pruebas");
        try {
            List<DetallePruebaDTO> pruebas = pruebaService.todas();
            log.info("Encontradas {} pruebas", pruebas.size());
            return ResponseEntity.ok().body(pruebas);
        } catch (Exception exception) {
            log.error("Error al buscar pruebas {}", exception.getMessage(), exception);
            return ResponseEntity.badRequest().build();
        }
    }
    // 1.c) Finalizar una prueba, permitiéndole al empleado agregar un comentario
    // sobre la misma.
    @PatchMapping("/finalizar-prueba/{id}")
    public ResponseEntity<String> finalizarPrueba(@PathVariable int id, @RequestBody FinPruebaDTO prueba) {
        log.info("Finalizando prueba");
        try {
            pruebaService.finalizarPrueba(id, prueba);
            log.info("Prueba finalizada {}", id);
            return ResponseEntity.ok().body("SE FINALIZO LA PRUEBA");
        } catch (Exception exception) {
            log.error("Error al finalizar prueba {}", exception.getMessage(), exception);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        log.info("Eliminando prueba");
        try {
            pruebaService.eliminarPrueba(id);
            log.info("Prueba eliminada {}", id);
            return ResponseEntity.ok().body("SE ELIMINO LA PRUEBA");
        }catch (Exception exception) {
            log.error("Error al eliminar prueba {}", exception.getMessage(), exception);
            return ResponseEntity.badRequest().build();
        }
    }

    //todos

    @PostMapping("/controlar-vehiculo")
    public ResponseEntity<Object> evaluarPosicion(@RequestBody PosicionDTO posicion) {
        log.info("Evaluando posicion");
        try {
            this.pruebaService.controlarVehiculo(posicion);
            log.info("Posicion evaluada con exito");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al evaluar posicion {}", exception.getMessage(), exception);
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




}