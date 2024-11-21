package com.example.geolocalizacion.models;

import com.example.geolocalizacion.models.Auxiliares.EmpleadoAux;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleIncidente {
    private List<DetallePrueba> incidentes;
}
