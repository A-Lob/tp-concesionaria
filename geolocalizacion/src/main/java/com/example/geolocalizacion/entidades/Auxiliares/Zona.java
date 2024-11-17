package com.example.geolocalizacion.entidades.Auxiliares;

import com.example.geolocalizacion.entidades.Agencia;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
//AGREGAR MAS ZONAS SI ES NECESARIO
public class Zona {


    private Coordenadas noroeste;
    private Coordenadas sureste;

}
