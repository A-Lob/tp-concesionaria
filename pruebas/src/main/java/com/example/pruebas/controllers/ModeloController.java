package com.example.pruebas.controllers;

import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.dtos.detallesDto.DetalleModeloDTO;
import com.example.pruebas.services.implementations.ModeloServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/modelos")
public class ModeloController {
    private ModeloServiceImpl modeloServiceImpl;

    public ModeloController(ModeloServiceImpl modeloServiceImpl) {
        this.modeloServiceImpl = modeloServiceImpl;
    }

    @PostMapping("/nuevo/{idMarca}/modelo")
    public ResponseEntity<String> crearModelo(@RequestBody ModeloDTO modeloDTO, @PathVariable int idMarca) {
        log.info("Agregando Modelo");
        try{
            modeloServiceImpl.nuevoModelo(modeloDTO, idMarca);
            log.info("Modelo agregado con exito");
            return ResponseEntity.ok().body("SE CREO EL MODELO");
        } catch (Exception e) {
            log.error("Error al agregar Modelo {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("NO SE PUDO AGREGAR EL NUEVO MODELO");
        }
    }

    @GetMapping("/{id}/modelo")
    public ResponseEntity<DetalleModeloDTO> findById(@PathVariable int id) {
        log.info("Buscando Modelo por ID {}", id);
        try{
            DetalleModeloDTO modelo =  modeloServiceImpl.obtenerDetalleModelo(id);
            log.info("Modelo encontrado: {}", modelo);
            return ResponseEntity.ok().body(modelo);
        }catch (Exception e){
            log.error("Error al buscar Modelo {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}/modificar/modelo")
    public ResponseEntity<String> modificarModelo(@PathVariable int id, @RequestBody ModeloDTO modeloDTO) {
        log.info("Modificando Modelo por id {}", id);
        try {
            modeloServiceImpl.modificar(id, modeloDTO);
            log.info("Modelo modificado {}", id);
            return ResponseEntity.ok().body("SE MODIFICO CON EXITO");
        } catch (Exception e) {
            log.error("Error al modificar Modelo {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("NO SE PUDO MODIFICAR EL MODELO");
        }
    }

    @DeleteMapping("{id}/eliminar/modelo")
    public ResponseEntity<String> eliminarModelo(@PathVariable int id) {
        log.info("Eliminando Modelo por id {}", id);
        try{
            modeloServiceImpl.eliminarModelo(id);
            log.info("Modelo eliminado {}", id);
            return ResponseEntity.ok().body("SE ELIMINO EL MODELO");
        } catch (Exception e) {
            log.error("Error al eliminar Modelo {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("NO SE PUDO ELIMINAR MODELO");
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<DetalleModeloDTO>> getAllModelos() {
        log.info("Buscando todos los modelos");
        try{
            List<DetalleModeloDTO> modelos = modeloServiceImpl.modelosAll();
            log.info("Encontrados {} modelos", modelos.size());
            return ResponseEntity.ok().body(modelos);
        } catch (Exception e) {
            log.error("Error al buscar Modelos {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

}
