package com.example.pruebas.controllers;

import com.example.pruebas.dtos.*;
import com.example.pruebas.dtos.detallesDto.DetallePruebaDTO;
import com.example.pruebas.services.implementations.PruebaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            pruebaService.agregar(solicitudPrueba);

            return new ResponseEntity<>(solicitudPrueba, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
    // 1.b) Listar pruebas en curso en un momento dado
    // Se envia la fecha-hora en la ruta
    // formato fecha: año-mes-dia, hora: hora
    @GetMapping("/listar-pruebas/{fecha}/{hora}")
    public ResponseEntity<List<DetallePruebaDTO>> getPruebas(@PathVariable String fecha,
                                                             @PathVariable String hora) {
        try {
            List<DetallePruebaDTO> pruebas = pruebaService.TodosPorFechas(fecha, hora);

            return ResponseEntity.ok().body(pruebas);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/todas")
    public ResponseEntity<List<DetallePruebaDTO>> getPruebasAll() {
        try {
            List<DetallePruebaDTO> pruebas = pruebaService.todas();
            return ResponseEntity.ok().body(pruebas);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
    // 1.c) Finalizar una prueba, permitiéndole al empleado agregar un comentario
    // sobre la misma.
    @PatchMapping("/finalizar-prueba/{id}")
    public ResponseEntity<String> finalizarPrueba(@PathVariable int id, @RequestBody FinPruebaDTO prueba) {
        try {
            pruebaService.finalizarPrueba(id, prueba);
            return ResponseEntity.ok().body("SE FINALIZO LA PRUEBA");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try {
            pruebaService.eliminarPrueba(id);
            return ResponseEntity.ok().body("SE ELIMINO LA PRUEBA");
        }catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
 /*


    //todos


    @PostMapping("/controlar-vehiculo")
    public ResponseEntity<Object> evaluarPosicion(@RequestBody PosicionDTO posicion) {
        try {
            this.pruebaService.controlarVehiculo(posicion);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
*/



}