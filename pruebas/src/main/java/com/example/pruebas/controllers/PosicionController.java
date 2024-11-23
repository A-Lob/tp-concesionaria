package com.example.pruebas.controllers;

import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.dtos.detallesDto.DetallePosicionDTO;
import com.example.pruebas.services.implementations.PosicionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/Posiciones")
public class PosicionController{
    private final PosicionServiceImpl posicionService;

    public PosicionController(PosicionServiceImpl posicionService) {
        this.posicionService = posicionService;
    }

    @PostMapping("/agregar/posicion")
    public ResponseEntity<String> crearPosicion(@RequestBody PosicionDTO posicionDTO){
        log.info("Agregando posicion");
        try {
            posicionService.agregar(posicionDTO);
            log.info("Posicion agregada con exito");
            return ResponseEntity.ok().body("Posicion agregado correctamente");
        }catch (Exception e){
            log.error("Error al agregar posicion {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("La posicion no puede ser agregada");
        }
    }

    @DeleteMapping("/{id}/eliminar/posicion")
    public ResponseEntity<String> eliminarPosicion(@PathVariable int id){
        log.info("Eliminando posicion");
        try {
            posicionService.eliminar(id);
            log.info("Posicion eliminada {}", id);
            return ResponseEntity.ok().body("Posicion eliminado correctamente");
        }catch (Exception e){
            log.error("Error al eliminar posicion {}", e.getMessage(), e);
            return  ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{latitudMin}/{longitudMin}/{latitudMax}/{longitudMax}/posiciones")
    public ResponseEntity<List<DetallePosicionDTO>> getAllCondicional(@PathVariable double latitudMax, @PathVariable double longitudMax,
                                                                       @PathVariable double latitudMin, @PathVariable double longitudMin){
        log.info("Buscando todas las posiciones dentro de un area");
        try{
            List<DetallePosicionDTO> posiciones =  posicionService.posicionesAll(latitudMin,longitudMin,longitudMax, latitudMax);
            log.info("Encontradas {} posiciones dentro del area", posiciones.size());
            return ResponseEntity.ok().body(posiciones);
        }catch (Exception e){
            log.error("Error al buscar posiciones {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<DetallePosicionDTO>> getAllPosiciones(){
        log.info("Buscando todas las posiciones");
        try {
            List<DetallePosicionDTO> posiciones = posicionService.posicionesAll();
            log.info("Encontradas {} posiciones", posiciones.size());
            return ResponseEntity.ok().body(posiciones);
        } catch (Exception e) {
            log.error("Error al buscar posiciones {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

}
