package com.example.geolocalizacion.services;

import com.example.geolocalizacion.models.DetallePrueba;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
@Service
public class PruebaService {
    private final WebClient webClient;

    //CUIDADO SI SE CAMBIA LA URL DE PRUEBAS SE CAMBIA TAMBIEN ESTA
    public PruebaService(WebClient webClient) {
        this.webClient = webClient.mutate()
                .baseUrl("http://localhost:8081/api/pruebas").build();
    }

    public List<DetallePrueba> obtenerDatosPrueba(){
        return  webClient.get()
                .uri("/todas")
                .retrieve()
                .bodyToFlux(DetallePrueba.class).collectList().block();
    }



}
