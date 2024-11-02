package com.example.pruebas.controllers;


import com.example.pruebas.dtos.InteresadoDTO;
import com.example.pruebas.models.Interesado;
import com.example.pruebas.services.interfaces.InteresadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //FALTAN LOS DEMAS METODOS

}
