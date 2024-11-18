package com.example.geolocalizacion.servicios;

import com.example.geolocalizacion.modelos.DetallePrueba;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
@Service
public class PruebaServicio {
    private final WebClient webClient;

    //CUIDADO SI SE CAMBIA LA URL DE PRUEBAS SE CAMBIA TAMBIEN ESTA
    public PruebaServicio(WebClient webClient) {
        this.webClient = webClient.mutate()
                .baseUrl("http://localhost:8081/api/pruebas/todas").build();
    }

    public List<DetallePrueba> obtenerDatosPrueba(){
        return  webClient.get()
                .retrieve()
                .bodyToFlux(DetallePrueba.class).collectList().block();
    }



}
