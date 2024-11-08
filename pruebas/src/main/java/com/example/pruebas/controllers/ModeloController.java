package com.example.pruebas.controllers;

import com.example.pruebas.dtos.MarcaDTO;
import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.dtos.detallesDto.DetalleModeloDTO;
import com.example.pruebas.models.Modelo;
import com.example.pruebas.services.implementations.ModeloServiceImpl;
import com.example.pruebas.services.interfaces.ModeloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/modelos")
public class ModeloController {
    private ModeloServiceImpl modeloServiceImpl;


    public ModeloController(ModeloServiceImpl modeloServiceImpl) {
        this.modeloServiceImpl = modeloServiceImpl;
    }

    @GetMapping("/todos/modelos")
    public ResponseEntity<List<DetalleModeloDTO>> getAllModelos() {
        try{
            return ResponseEntity.ok().body(modeloServiceImpl.modelosAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleModeloDTO> getModelo(@PathVariable int id) {
        try{
           DetalleModeloDTO modelo =  modeloServiceImpl.modelo(id);
           return ResponseEntity.ok().body(modelo);



        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/nuevo/{idMarca}/modelo")
    public ResponseEntity<String> nuevoModelo(@RequestBody ModeloDTO modeloDTO, @PathVariable int idMarca) {
        try{
            modeloServiceImpl.nuevoModelo(modeloDTO, idMarca);
            return ResponseEntity.ok().body("SE CREO EL MODELO");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO SE PUDO AGREGAR EL NUEVO MODELO");
        }
    }

    @DeleteMapping("/eliminar/{id}/modelo")
    public ResponseEntity<String> eliminarModelo(@PathVariable int id) {
        try{
            modeloServiceImpl.eliminarModelo(id);
            return ResponseEntity.ok().body("SE ELIMINO EL MODELO");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO SE PUDO ELIMINAR MODELO");
        }
    }
    @PatchMapping("/{id}/modificar/modelo")
    public ResponseEntity<String> modificarModelo(@PathVariable int id, @RequestBody ModeloDTO modeloDTO) {
        try {
                modeloServiceImpl.modificar(id, modeloDTO);
                return ResponseEntity.ok().body("SE MODIFICO CON EXITO");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO SE PUDO MODIFICAR EL MODELO");
        }
    }

}
