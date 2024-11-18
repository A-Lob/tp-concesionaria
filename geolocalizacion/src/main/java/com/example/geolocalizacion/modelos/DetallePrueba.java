package com.example.geolocalizacion.modelos;

import com.example.geolocalizacion.modelos.Auxiliares.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePrueba {
    private Interesado interesado;
    private Vehiculo vehiculo;
    private Empleado empleado;
    private FinPrueba finPrueba;
}
