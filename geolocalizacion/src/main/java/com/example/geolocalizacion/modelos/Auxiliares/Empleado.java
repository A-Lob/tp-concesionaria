package com.example.geolocalizacion.modelos.Auxiliares;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    private int legajo;
    private String nombre;
    private String apellido;
    private String email;
}
