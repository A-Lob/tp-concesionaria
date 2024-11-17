package com.example.geolocalizacion.servicios;

import com.example.geolocalizacion.clientes.WebClientConfig;
import com.example.geolocalizacion.entidades.Agencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

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
