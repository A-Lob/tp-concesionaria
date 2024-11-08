package com.example.pruebas.controllers;

import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.dtos.detallesDto.DetallePosicionDTO;
import com.example.pruebas.services.implementations.PosicionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Posiciones")
public class PosicionController{
    private PosicionServiceImpl posicionService;

    public PosicionController(PosicionServiceImpl posicionService) {
        this.posicionService = posicionService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<DetallePosicionDTO>> findAll(){
        try {
            List<DetallePosicionDTO> posiciones = posicionService.posicionAll();
            return ResponseEntity.ok().body(posiciones);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{latitudMax}/{longitudMax}/posiciones")
    public ResponseEntity<List<DetallePosicionDTO>> findAllCondicional(@PathVariable double latitudMax, @PathVariable double longitudMax){
        try{
           List<DetallePosicionDTO> posiciones =  posicionService.posicionAll(longitudMax, latitudMax);
            return ResponseEntity.ok().body(posiciones);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/agregar/posicion")
    public ResponseEntity<String> agregar(@RequestBody PosicionDTO posicionDTO){
        try {
            posicionService.agregar(posicionDTO);
            return ResponseEntity.ok().body("Posicion agregado correctamente");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("La posicion no puede ser agregada");
        }
    }
    @DeleteMapping("/{id}/eliminar/posicion")
    public ResponseEntity<String> eliminar(@PathVariable int id){
        try {
            posicionService.eliminar(id);
            return ResponseEntity.ok().body("Posicion eliminado correctamente");
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }


}
