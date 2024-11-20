package com.example.geolocalizacion.services;


import com.example.geolocalizacion.models.DetallePrueba;
import com.example.geolocalizacion.models.DetalleVehiculo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class VehiculoService {
    private final WebClient webClient;

    //CUIDADO SI SE CAMBIA LA URL DE PRUEBAS SE CAMBIA TAMBIEN ESTA
    public VehiculoService(WebClient webClient) {
        this.webClient = webClient.mutate()
                .baseUrl("http://localhost:8081/api/vehiculos").build();
    }

    public List<DetalleVehiculo> obtenerDatosVehiculos(){
        return  webClient.get()
                .uri("/todos")
                .retrieve()
                .bodyToFlux(DetalleVehiculo.class).collectList().block();
    }
    public  DetalleVehiculo obtenerDatosVehiculo(String idVehiculo){
        return  webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/buscar/{id}").build(idVehiculo))
                .retrieve()
                .bodyToMono(DetalleVehiculo.class).block();
    }
}
