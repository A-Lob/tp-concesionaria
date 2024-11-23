package com.example.pruebas.controllers;


import com.example.pruebas.dtos.InteresadoDTO;
import com.example.pruebas.dtos.detallesDto.DetalleInteresadoDTO;
import com.example.pruebas.services.implementations.InteresadoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//FALTA VALIDACIONES DE ATRIBUTOS
@Slf4j
@RestController
@RequestMapping("/api/pruebas/interesado")
public class InteresadoController {

    private final InteresadoServiceImpl interesadoService;

    public InteresadoController(InteresadoServiceImpl interesadoService) {
        this.interesadoService = interesadoService;
    }

    @PostMapping("/nuevoInteresado")
    public ResponseEntity<String> crearInteresado(@RequestBody InteresadoDTO interesado) {
        log.info("Agregando Interesado");
        try {
            interesadoService.agregar(interesado);
            log.info("Interesado agregado con exito");
            return ResponseEntity.ok().body("Nuevo Interesado");
        } catch (Exception e) {
            log.error("Error al agregar interesado {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("id/interesado")
    public ResponseEntity<DetalleInteresadoDTO> findById(@PathVariable int id){
        log.info("Buscando Interesado por ID {}", id);
        try {
            DetalleInteresadoDTO interesado = interesadoService.obtenerDetalleInteresado(id);
            log.info("Interesado encontrado: {}", interesado);
            return ResponseEntity.ok().body(interesado);
        }catch (Exception e){
            log.error("Error al obtener interesado {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("{id}/modificar/interesado")
    public ResponseEntity<Object> updateInteresado(@RequestBody InteresadoDTO interesado, @PathVariable int id) {
        log.info("Modificando Interesado por id {}", id);
        try{
            interesadoService.actualizar(interesado, id);
            log.info("Interesado modificado {}", id);
            return ResponseEntity.ok().body("Interesado Actualizado");
        }catch (Exception e){
            log.error("Error al modificar interesado {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}/eliminar/interesado")
    public ResponseEntity<String> deleteInteresado(@PathVariable int id) {
        log.info("Eliminando Interesado por id {}", id);
        try{
            interesadoService.eliminar(id);
            log.info("Interesado eliminado {}", id);
            return ResponseEntity.ok().body("Interesado eliminado");
        }catch (Exception e){
            log.error("Error al eliminar interesado {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/interesados")
    public ResponseEntity<List<DetalleInteresadoDTO>> getAllInteresados() {
        log.info("Buscando todos los interesados");
        try {
            List<DetalleInteresadoDTO> interesados = interesadoService.interesadosAll();
            log.info("Encontrados {} Interesados", interesados.size());
            return ResponseEntity.ok().body(interesados);
        } catch (Exception e) {
            log.error("Error al obtener interesados {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }

    }


}
