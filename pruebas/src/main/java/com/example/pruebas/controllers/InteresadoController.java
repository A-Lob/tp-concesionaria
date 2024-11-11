package com.example.pruebas.controllers;


import com.example.pruebas.dtos.InteresadoDTO;
import com.example.pruebas.models.Interesado;
import com.example.pruebas.services.interfaces.InteresadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//FALTA VALIDACIONES DE ATRIBUTOS
@RestController
@RequestMapping("/api/pruebas/interesado")
public class InteresadoController {

    private final InteresadoService interesadoService;

    public InteresadoController(InteresadoService interesadoService) {
        this.interesadoService = interesadoService;
    }

    @PostMapping("/nuevoInteresado")
    public ResponseEntity<Object> nuevoInteresado(@RequestBody InteresadoDTO interesado) {
        try {
            Interesado nuevo = new Interesado();
            nuevo.setNombre(interesado.getNombre());
            nuevo.setTipoDocumento(interesado.getTipoDocumento());
            nuevo.setDocumento(String.valueOf(interesado.getNumDocumento()));
            nuevo.setApellido(interesado.getApellido());
            nuevo.setFechaVencimientoLicencia(interesado.getFechaVencimientoLicencia());
            nuevo.setNumeroLicencia(interesado.getNumeroLicencia());

            this.interesadoService.add(nuevo);
            return ResponseEntity.ok().body("Nuevo Interesado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/interesados")
    public ResponseEntity<List<Interesado>> getAllInteresados() {
        try {
            List<Interesado> interesados = interesadoService.findAll();

            return ResponseEntity.ok(interesados);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/interesadoId/{id}")
    public ResponseEntity<Object> interesado(@PathVariable int id){
        try {
            Interesado interesado = this.interesadoService.findById(id);
            InteresadoDTO interesadoDTO = new InteresadoDTO(
                    interesado.getTipoDocumento(),
                    Integer.parseInt(interesado.getDocumento()),
                    interesado.getNombre(),
                    interesado.getApellido(),
                    interesado.getFechaVencimientoLicencia(),
                    interesado.getNumeroLicencia(),
                    interesado.getEstado()
            );
            return ResponseEntity.ok().body(interesadoDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteInteresado(@PathVariable int id) {
        try{
            interesadoService.delete(id);
            return "OK!";

        }catch (Exception e){
            return "Error al eliminar el interesado";
        }

    }

    @PutMapping("/modificacionInteresado/{id}")
    public ResponseEntity<Object> updateInteresado(@RequestBody InteresadoDTO interesado, @PathVariable int id) {
        try{
            Interesado interesadoUpdate = interesadoService.findById(id);
            interesadoUpdate.setTipoDocumento(interesado.getTipoDocumento());
            interesadoUpdate.setDocumento(String.valueOf(interesado.getNumDocumento()));
            interesadoUpdate.setNombre(interesado.getNombre());
            interesadoUpdate.setApellido(interesado.getApellido());
            interesadoUpdate.setFechaVencimientoLicencia(interesado.getFechaVencimientoLicencia());
            interesadoUpdate.setNumeroLicencia(interesado.getNumeroLicencia());
            interesadoUpdate.setEstado(interesado.getEstado());

            interesadoService.update(interesadoUpdate);
            return ResponseEntity.ok().body("Interesado Actualizado");

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

}
