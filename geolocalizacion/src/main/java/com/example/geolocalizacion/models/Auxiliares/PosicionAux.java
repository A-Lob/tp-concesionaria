package com.example.geolocalizacion.models.Auxiliares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosicionAux {
    private int idVehiculo;
    private double latitud;
    private double longitud;
}
