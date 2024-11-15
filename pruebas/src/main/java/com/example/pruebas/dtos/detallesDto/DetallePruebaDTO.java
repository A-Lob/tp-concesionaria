package com.example.pruebas.dtos.detallesDto;

import com.example.pruebas.dtos.EmpleadoDTO;
import com.example.pruebas.dtos.FinPruebaDTO;
import com.example.pruebas.dtos.InteresadoDTO;
import com.example.pruebas.dtos.VehiculoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePruebaDTO {
    private InteresadoDTO interesado;
    private VehiculoDTO vehiculo;
    private EmpleadoDTO empleado;
    private FinPruebaDTO finPrueba;
}
