package com.example.geolocalizacion.models.Auxiliares;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoAux {
    private int legajo;
    private String nombre;
    private String apellido;
    private String email;
}
