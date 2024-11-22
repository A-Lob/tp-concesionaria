package com.example.pruebas.controllers;

import com.example.pruebas.dtos.PromocionDTO;
import com.example.pruebas.dtos.PromocionRequest;
import com.example.pruebas.dtos.detallesDto.DetallePromocionDTO;
import com.example.pruebas.services.implementations.PromocionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/promociones")
public class PromocionController {
    private final PromocionServiceImpl promocionService;

    public PromocionController(PromocionServiceImpl promocionService) {
        this.promocionService = promocionService;
    }

    @PostMapping("/nueva-promocion")
    public ResponseEntity<Object> crearPromocion(@RequestBody PromocionRequest request) {
        log.info("Creando promocion");
        try {
            promocionService.nuevaPromocion(request.getPromocion(),
                    request.getInteresadosId(),
                    request.getVehiculosId()
            );
            log.info("Promocion creada con exito");

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}/promocion")
    public ResponseEntity<DetallePromocionDTO> findById(@PathVariable int id) {
        log.info("Buscando promocion por id {}", id);
        try {
            DetallePromocionDTO promocion = promocionService.obtenerDetallePromocion(id);
            log.info("Promocion encontrada: {}", promocion);
            return ResponseEntity.ok().body(promocion);
        }catch (Exception exception) {
            log.error("Error al buscar Promocion {}", exception.getMessage(), exception);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}/eliminar")
    public ResponseEntity<Object> eliminarPromocion(@PathVariable Integer id) {
        log.info("Eliminando promocion por id {}", id);
        try {
            promocionService.eliminarPromocion(id);
            log.info("Promocion eliminado {}", id);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            log.error("Error al eliminar la Promocion {}", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/promocionar")
    public ResponseEntity<Object> enviarPromociones(@RequestBody Integer id) {
        log.info("Enviando promocion por id {}", id);
        try {
            this.promocionService.enviarPromociones(id);
            log.info("Promocion enviada con exito");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al enviar la Promocion {}", id);
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/todas")
    public ResponseEntity<List<DetallePromocionDTO>> getPromocionesAll() {
        log.info("Buscando todas las Promociones");
        try {
            List<DetallePromocionDTO> promociones = promocionService.promocionesAll();
            log.info("Encontradas {} Promociones", promociones.size());
            return ResponseEntity.ok().body(promociones);
        } catch (Exception exception) {
            log.error("Error al encontrar las Promociones", exception);
            return ResponseEntity.badRequest().build();
        }
    }

}
