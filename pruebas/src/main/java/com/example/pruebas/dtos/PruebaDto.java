package com.example.pruebas.dtos;

import com.example.pruebas.models.Interesado;
import com.example.pruebas.models.Vehiculo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PruebaDto {

    private InteresadoDto interesado;
    private VehiculoDto vehiculo;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

}
