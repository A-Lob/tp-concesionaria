package com.example.pruebas.controllers;

import com.example.pruebas.dtos.EmpleadoDTO;
import com.example.pruebas.dtos.detallesDto.DetalleEmpleadoDTO;
import com.example.pruebas.models.Empleado;
import com.example.pruebas.services.implementations.EmpleadoServiceImpl;
import com.example.pruebas.services.interfaces.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pruebas/empleado")
public class EmpleadoController {

    private final EmpleadoServiceImpl empleadoService;

    public EmpleadoController(EmpleadoServiceImpl empleadoService) {

        this.empleadoService = empleadoService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<DetalleEmpleadoDTO>> listar() {
        try{
            List<DetalleEmpleadoDTO> empleados = empleadoService.todos();
            return ResponseEntity.ok().body(empleados);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/{legajo}/empleado")
    public ResponseEntity<DetalleEmpleadoDTO> buscar(@PathVariable int legajo) {
        try{
            DetalleEmpleadoDTO empleado = empleadoService.empleado(legajo);
            return ResponseEntity.ok().body(empleado);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/agregar/nuevoEmpleado")
    public  ResponseEntity<String> agregarNuevoEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        try{
            empleadoService.nuevoEmpleado(empleadoDTO);
            return ResponseEntity.ok().body("Empleado agregado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO SE PUDO AGREGAR");
        }
    }

    @DeleteMapping("/{legajo}/eliminar/empleado")
    public  ResponseEntity<String> eliminarEmpleado(@PathVariable int legajo) {
        try{
            empleadoService.eliminarEmpleado(legajo);
            return ResponseEntity.ok().body("Empleado eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO SE PUDO ELIMINAR");
        }
    }
    @PatchMapping("/{legajo}/modificar/empleado")
    public ResponseEntity<String> modificarEmpleado(@PathVariable int legajo, @RequestBody EmpleadoDTO empleadoDTO) {
        try {
            empleadoService.modificarEmpleado(legajo, empleadoDTO);
            return ResponseEntity.ok().body("Empleado modificado correctamente");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO SE PUDO MODIFICAR");
        }
    }

}
