package com.example.geolocalizacion.services;

import com.example.geolocalizacion.models.Agencia;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeolocalizacionService {
    private final WebClient webClient;

    public GeolocalizacionService(WebClient webClient) {
        this.webClient = webClient.mutate()
                .baseUrl("https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend/api/v1/configuracion/").build();
    }

    public Agencia obtenerDatosAgencia(){
        return webClient.get().retrieve().bodyToMono(Agencia.class).block();
    }
}
