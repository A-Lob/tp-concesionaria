package com.example.geolocalizacion.services;

import org.springframework.stereotype.Service;

@Service
public class IncidentesService {

    private PruebaService pruebaService;
    private GeolocalizacionService geolocalizacionService;

    public IncidentesService(PruebaService pruebaService, GeolocalizacionService geolocalizacionService) {
        this.pruebaService = pruebaService;
        this.geolocalizacionService = geolocalizacionService;
    }


}
