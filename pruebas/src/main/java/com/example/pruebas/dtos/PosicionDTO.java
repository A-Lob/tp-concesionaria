package com.example.pruebas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosicionDTO {

    private int idVehiculo;
    private double latitud;
    private double longitud;

}
