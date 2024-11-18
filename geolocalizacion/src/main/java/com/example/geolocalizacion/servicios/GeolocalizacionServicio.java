package com.example.geolocalizacion.servicios;

import com.example.geolocalizacion.modelos.Agencia;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeolocalizacionServicio{
    private final WebClient webClient;

    public GeolocalizacionServicio(WebClient webClient) {
        this.webClient = webClient.mutate()
                .baseUrl("https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend/api/v1/configuracion/").build();
    }

    public Agencia obtenerDatosAgencia(){
        return webClient.get().retrieve().bodyToMono(Agencia.class).block();
    }
}
