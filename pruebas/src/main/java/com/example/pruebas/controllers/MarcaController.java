package com.example.pruebas.controllers;

import com.example.pruebas.dtos.MarcaDTO;
import com.example.pruebas.dtos.detallesDto.DetalleMarcaDTO;
import com.example.pruebas.services.implementations.MarcaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/marcas")
public class MarcaController {
    private MarcaServiceImpl marcaService;

    public MarcaController(MarcaServiceImpl marcaService) {
        this.marcaService = marcaService;
    }

    @PostMapping("/agregar/marca")
    public ResponseEntity<String> crearMarca(@RequestBody MarcaDTO marcaDTO) {
        log.info("Agregando Marca");
        try{
            marcaService.agregar(marcaDTO);
            log.info("Marca agregada con exito");
            return ResponseEntity.ok().body("SE AGREGO CON EXITO");
        }catch (Exception e){
            log.error("Error al agregar Marca {}",e.getMessage(), e);
            return ResponseEntity.badRequest().body("NO SE PUEDO AGREGAR MARCA");
        }
    }

    @GetMapping("/{id}/marca")
    public ResponseEntity<DetalleMarcaDTO> findById(@PathVariable int id) {
        log.info("Buscando Marca por ID {}", id);
        try{
            DetalleMarcaDTO marca = marcaService.obtenerDetalleMarca(id);
            log.info("Marca encontrada: {}",marca);
            return ResponseEntity.ok().body(marca);
        }catch (Exception e){
            log.error("Error al obtener la Marca {}",e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}/modificar/marca")
    public ResponseEntity<String> modificarMarca(@PathVariable int id, @RequestBody MarcaDTO marcaDTO) {
        log.info("Modificando Marca por id {}", id);
        try {
            marcaService.modificar(marcaDTO,id);
            log.info("Marca modificada {}", id);
            return ResponseEntity.ok().body("SE MODIFICO CON EXITO");

        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}/eliminar/marca")
    public ResponseEntity<String> eliminarMarca(@PathVariable int id) {
        log.info("Eliminando Marca por Id {}", id);
        try{
            marcaService.eliminar(id);
            log.info("Marca eliminada {}",id);
            return ResponseEntity.ok().body("LA MARCA FUE ELIMINADA CON EXITO");
        }catch (Exception e){
            log.error("Error al eliminar la Marca {}",e.getMessage(), e);
            return ResponseEntity.badRequest().body("LA MARCA FUE ELIMINADA NO EXISTE");
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<DetalleMarcaDTO>> getAllMarcas() {
        log.info("Buscando todas las Marcas");
        try{
            List<DetalleMarcaDTO> marcas = marcaService.marcasAll();
            log.info("Encontradas {} Marcas", marcas.size());
            return ResponseEntity.ok().body(marcas);
        }catch (Exception e){
            log.error("Error al obtener las Marcas {}",e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }
}
