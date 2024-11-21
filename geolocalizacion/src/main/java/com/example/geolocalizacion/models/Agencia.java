package com.example.geolocalizacion.models;

import com.example.geolocalizacion.models.Auxiliares.Coordenadas;
import com.example.geolocalizacion.models.Auxiliares.Zona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agencia {
    private Coordenadas coordenadasAgencia;
    private int radioAdmitidoKm;
    private List<Zona> zonasRestringidas;
}
