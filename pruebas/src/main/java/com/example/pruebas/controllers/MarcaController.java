package com.example.pruebas.controllers;

import com.example.pruebas.dtos.MarcaDTO;
import com.example.pruebas.dtos.detallesDto.DetalleMarcaDTO;
import com.example.pruebas.models.Marca;
import com.example.pruebas.services.implementations.MarcaServiceImpl;
import com.example.pruebas.services.interfaces.MarcaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/marcas")
public class MarcaController {
    private MarcaServiceImpl marcaService;

    public MarcaController(MarcaServiceImpl marcaService) {
        this.marcaService = marcaService;
    }
    @GetMapping("/todos")
    public ResponseEntity<List<DetalleMarcaDTO>> findAll() {
        try{
           List<DetalleMarcaDTO> marcas = marcaService.MarcasAll();
           return ResponseEntity.ok().body(marcas);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{id}/marca")
    public ResponseEntity<DetalleMarcaDTO> findById(@PathVariable int id) {
        try{
            DetalleMarcaDTO marca = marcaService.marca(id);
            return ResponseEntity.ok().body(marca);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}/eliminar/marca")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try{
            marcaService.eliminar(id);
            return ResponseEntity.ok().body("LA MARCA FUE ELIMINADA CON EXITO");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("LA MARCA FUE ELIMINADA NO EXISTE");
        }
    }
    @PatchMapping("/{id}/modificar/marca")
    public ResponseEntity<String> modificar(@PathVariable int id, @RequestBody MarcaDTO marcaDTO) {
        try {
            marcaService.modificar(marcaDTO,id);
            return ResponseEntity.ok().body("SE MODIFICO CON EXITO");

        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/agregar/marca")
    public ResponseEntity<String> agregar(@RequestBody MarcaDTO marcaDTO) {
        try{
            marcaService.agregar(marcaDTO);
            return ResponseEntity.ok().body("SE AGREGO CON EXITO");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("NO SE PUEDO AGREGAR MARCA");
        }
    }
}
