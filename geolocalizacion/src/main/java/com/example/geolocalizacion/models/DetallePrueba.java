package com.example.geolocalizacion.models;

import com.example.geolocalizacion.models.Auxiliares.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePrueba {
    private PruebaAux prueba;
    private InteresadoAux interesado;
    private VehiculoAux vehiculo;
    private EmpleadoAux empleado;
    private FinPruebaAux finPrueba;
}
