package com.example.geolocalizacion.services;

import com.example.geolocalizacion.models.Agencia;
import com.example.geolocalizacion.models.Auxiliares.PosicionAux;
import com.example.geolocalizacion.models.DetallePrueba;
import com.example.geolocalizacion.models.DetalleVehiculo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncidentesService {

    private PruebaService pruebaService;
    private GeolocalizacionService geolocalizacionService;
    private VehiculoService vehiculoService;

    public IncidentesService(PruebaService pruebaService,
                             GeolocalizacionService geolocalizacionService,
                             VehiculoService vehiculoService) {
        this.pruebaService = pruebaService;
        this.geolocalizacionService = geolocalizacionService;
        this.vehiculoService = vehiculoService;
    }

    private double calculoDistancia(double lat, double lon) {
        double R = 6378;
        Agencia agencia = geolocalizacionService.obtenerDatosAgencia();

        double latRad = Math.toRadians(lat);
        double lonRad = Math.toRadians(lon);

        double latAgenciaRad = Math.toRadians(agencia.getCoordenadasAgencia().getLat());
        double lonRadAgenciaRad = Math.toRadians(agencia.getCoordenadasAgencia().getLon());

        double difLatitud = latAgenciaRad - latRad;
        double difLongitud = lonRadAgenciaRad - lonRad;
        // formula

        double a = Math.pow(Math.sin(difLatitud / 2), 2)
                + Math.cos(latRad) * Math.cos(latAgenciaRad) * Math.pow(Math.sin(difLongitud / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }


    public List<DetallePrueba> incidentes() {
        List<DetallePrueba> pruebas = pruebaService.obtenerDatosPrueba();

        List<DetalleVehiculo> vehiculos = pruebas.stream().map(p ->
                vehiculoService.obtenerDatosVehiculo(String.valueOf(p.getPrueba().getIdVehiculo()))
        ).toList();
        List<PosicionAux> posicionesFiltradas = vehiculos.stream()
                .flatMap(v -> v.getPosiciones().stream())
                .filter(e -> calculoDistancia(e.getLatitud(), e.getLongitud()) >
                        geolocalizacionService.obtenerDatosAgencia().getRadioAdmitidoKm())
                .toList();
        vehiculos = posicionesFiltradas.stream().map(p -> {
            return vehiculoService.obtenerDatosVehiculo(String.valueOf(p.getIdVehiculo()));

        }).toList();
        List<DetallePrueba> pruebasFinal = pruebas.stream().filter(p ->
                posicionesFiltradas.stream().anyMatch(f -> f.getIdVehiculo() == p.getVehiculo().getId())).toList();

      return pruebasFinal;

}

}
