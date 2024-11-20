package com.example.geolocalizacion.models;

import com.example.geolocalizacion.models.Auxiliares.ModeloAux;
import com.example.geolocalizacion.models.Auxiliares.PosicionAux;
import com.example.geolocalizacion.models.Auxiliares.PruebaAux;
import com.example.geolocalizacion.models.Auxiliares.VehiculoAux;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVehiculo {

    private VehiculoAux vehiculo;
    private List<PruebaAux> pruebas;
    private List<PosicionAux> posiciones;
    private ModeloAux modelo;


}
