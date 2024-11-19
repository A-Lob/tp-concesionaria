package com.example.pruebas.controllers;

import com.example.pruebas.dtos.PromocionRequest;
import com.example.pruebas.dtos.detallesDto.DetallePromocionDTO;
import com.example.pruebas.services.implementations.PromocionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promociones")
public class PromocionController {
    private final PromocionServiceImpl promocionService;

    public PromocionController(PromocionServiceImpl promocionService) {
        this.promocionService = promocionService;
    }

    @PostMapping("/nueva-promocion")
    public ResponseEntity<Object> crearPromocion(@RequestBody PromocionRequest request) {
        try {
            promocionService.nuevaPromocion(
                    request.getPromocion(),
                    request.getInteresadosId(),
                    request.getVehiculosId()
            );

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todas")
    public ResponseEntity<List<DetallePromocionDTO>> getPromocionesAll() {
        try {
            List<DetallePromocionDTO> promociones = promocionService.PromocionesAll();
            return ResponseEntity.ok().body(promociones);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}/eliminar")
    public ResponseEntity<Object> eliminarPromocion(@PathVariable Integer id) {
        try {
            promocionService.eliminarPromocion(id);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/promocionar")
    public ResponseEntity<Object> enviarPromociones(@RequestBody Integer id) {
        try {
            this.promocionService.enviarPromociones(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
