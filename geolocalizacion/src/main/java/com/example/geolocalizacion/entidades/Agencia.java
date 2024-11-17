package com.example.geolocalizacion.entidades;

import com.example.geolocalizacion.entidades.Auxiliares.Coordenadas;
import com.example.geolocalizacion.entidades.Auxiliares.Zona;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agencia {
    private Coordenadas coordenadasAgencia;
    private int radioAdmitidoKm;
    private List<Zona> zonasRestringidas;
}
