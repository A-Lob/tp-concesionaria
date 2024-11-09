package com.example.pruebas.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {
    private int legajo;
    private String nombre;
    private String apellido;
    private String email;
}
