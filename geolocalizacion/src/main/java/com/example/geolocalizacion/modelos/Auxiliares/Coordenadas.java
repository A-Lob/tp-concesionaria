package com.example.geolocalizacion.modelos.Auxiliares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordenadas {
    private double lat;
    private double lon;

}
