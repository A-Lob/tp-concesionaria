package com.example.pruebas.controllers;

import com.example.pruebas.dtos.EmpleadoDTO;
import com.example.pruebas.dtos.detallesDto.DetalleEmpleadoDTO;
import com.example.pruebas.services.implementations.EmpleadoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/pruebas/empleado")
public class EmpleadoController {

    private final EmpleadoServiceImpl empleadoService;

    public EmpleadoController(EmpleadoServiceImpl empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/agregar/nuevoEmpleado")
    public  ResponseEntity<String> crearNuevoEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        log.info("Agregando Empleado");
        try{
            empleadoService.nuevoEmpleado(empleadoDTO);
            log.info("Empleado agregado con exito");
            return ResponseEntity.ok().body("Empleado agregado correctamente");
        } catch (Exception e) {
            log.error("Error al agregar Empleado {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("NO SE PUDO AGREGAR");
        }
    }

    @GetMapping("/{legajo}/empleado")
    public ResponseEntity<DetalleEmpleadoDTO> findByLegajo(@PathVariable int legajo) {
        log.info("Buscando empleado por legajo {}", legajo);
        try{
            DetalleEmpleadoDTO empleado = empleadoService.obtenerDetalleEmpleado(legajo);
            log.info("Empleado encontrado: {}", empleado);
            return ResponseEntity.ok().body(empleado);
        }catch (Exception e){
            log.error("Error al buscar Empleado {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{legajo}/modificar/empleado")
    public ResponseEntity<String> modificarEmpleado(@PathVariable int legajo, @RequestBody EmpleadoDTO empleadoDTO) {
        log.info("Modificando empleado por legajo {}", legajo);
        try {
            empleadoService.modificarEmpleado(legajo, empleadoDTO);
            log.info("Empleado modificado {}", legajo);
            return ResponseEntity.ok().body("Empleado modificado correctamente");
        } catch (Exception e) {
            log.error("Error al modificar Empleado {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("NO SE PUDO MODIFICAR");
        }
    }


    @DeleteMapping("/{legajo}/eliminar/empleado")
    public  ResponseEntity<String> eliminarEmpleado(@PathVariable int legajo) {
        log.info("Eliminando Empleado por legajo {}", legajo);
        try{
            empleadoService.eliminarEmpleado(legajo);
            log.info("Empleado eliminado {}", legajo);
            return ResponseEntity.ok().body("Empleado eliminado correctamente");
        } catch (Exception e) {
            log.error("Error al eliminar Empleado {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("NO SE PUDO ELIMINAR");
        }
    }


    @GetMapping("/todos")
    public ResponseEntity<List<DetalleEmpleadoDTO>> getAllEmpleados() {
        log.info("Buscando todos los empleados");
        try{
            List<DetalleEmpleadoDTO> empleados = empleadoService.empleadosAll();
            log.info("Se encontraron {} Empleados", empleados.size());
            return ResponseEntity.ok().body(empleados);
        }catch (Exception e){
            log.error("Error al listar Empleados {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }

    }

}
