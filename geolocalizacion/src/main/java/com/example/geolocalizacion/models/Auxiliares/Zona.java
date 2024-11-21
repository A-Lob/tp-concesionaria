package com.example.geolocalizacion.models.Auxiliares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
//AGREGAR MAS ZONAS SI ES NECESARIO
public class Zona {


    private Coordenadas noroeste;
    private Coordenadas sureste;

}
