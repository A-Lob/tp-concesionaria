package com.example.pruebas.controllers;


import com.example.pruebas.dtos.InteresadoDTO;
import com.example.pruebas.dtos.detallesDto.DetalleInteresadoDTO;
import com.example.pruebas.models.Interesado;
import com.example.pruebas.services.implementations.InteresadoServiceImpl;
import com.example.pruebas.services.interfaces.InteresadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//FALTA VALIDACIONES DE ATRIBUTOS
@RestController
@RequestMapping("/api/pruebas/interesado")
public class InteresadoController {

    private final InteresadoServiceImpl interesadoService;

    public InteresadoController(InteresadoServiceImpl interesadoService) {
        this.interesadoService = interesadoService;
    }

    @PostMapping("/nuevoInteresado")
    public ResponseEntity<String> nuevoInteresado(@RequestBody InteresadoDTO interesado) {
        try {
            interesadoService.agregar(interesado);
            return ResponseEntity.ok().body("Nuevo Interesado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/interesados")
    public ResponseEntity<List<DetalleInteresadoDTO>> getAllInteresados() {
        try {
            List<DetalleInteresadoDTO> interesados = interesadoService.todos();
            return ResponseEntity.ok().body(interesados);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/interesadoId/{id}")
    public ResponseEntity<DetalleInteresadoDTO> interesado(@PathVariable int id){
        try {

            DetalleInteresadoDTO interesado = interesadoService.interesado(id);
            return ResponseEntity.ok().body(interesado);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteInteresado(@PathVariable int id) {
        try{
            interesadoService.eliminar(id);
            return ResponseEntity.ok().body("Interesado eliminado");

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PatchMapping("/modificacionInteresado/{id}")
    public ResponseEntity<Object> updateInteresado(@RequestBody InteresadoDTO interesado, @PathVariable int id) {
        try{

            interesadoService.actualizar(interesado, id);
            return ResponseEntity.ok().body("Interesado Actualizado");

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

}
