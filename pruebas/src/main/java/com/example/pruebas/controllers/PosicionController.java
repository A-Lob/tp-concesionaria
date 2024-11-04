package com.example.pruebas.controllers;

import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.models.Posicion;
import com.example.pruebas.services.implementations.PosicionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Posiciones")
public class PosicionController{
    private PosicionServiceImpl posicionService;

    public PosicionController(PosicionServiceImpl posicionService) {
        this.posicionService = posicionService;
    }

    @GetMapping("/todas")
    public ResponseEntity<List<PosicionDTO>> getPosicionesAll(){
        try{
           List<Posicion> posicion = posicionService.findAll();
           List<PosicionDTO> dto = posicion.stream().map(p -> {
               PosicionDTO d = new PosicionDTO(
                       p.getVehiculo().getId(),
                       p.getLatitud(),
                       p.getLongitud());
               return d;
           }).toList();

            return ResponseEntity.ok().body(dto);


        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
