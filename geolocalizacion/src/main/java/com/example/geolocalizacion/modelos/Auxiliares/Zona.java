package com.example.geolocalizacion.modelos.Auxiliares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
//AGREGAR MAS ZONAS SI ES NECESARIO
public class Zona {


    private Coordenadas noroeste;
    private Coordenadas sureste;

}
