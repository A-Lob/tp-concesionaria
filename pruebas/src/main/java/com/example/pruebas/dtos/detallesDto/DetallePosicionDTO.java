package com.example.pruebas.dtos.detallesDto;

import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.dtos.VehiculoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePosicionDTO {
    private PosicionDTO posicion;
    private VehiculoDTO vehiculo;
}
