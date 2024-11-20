package com.example.pruebas.dtos.detallesDto;

import com.example.pruebas.dtos.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePruebaDTO {
    private PruebaDTO prueba;
    private InteresadoDTO interesado;
    private VehiculoDTO vehiculo;
    private EmpleadoDTO empleado;
    private FinPruebaDTO finPrueba;
}
