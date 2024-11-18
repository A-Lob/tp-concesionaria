package com.example.geolocalizacion.modelos;

import com.example.geolocalizacion.modelos.Auxiliares.Coordenadas;
import com.example.geolocalizacion.modelos.Auxiliares.Zona;
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
