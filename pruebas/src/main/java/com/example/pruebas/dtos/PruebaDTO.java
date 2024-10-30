package com.example.pruebas.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PruebaDTO {

    private int idInteresado;
    private int idVehiculo;
    private LocalDateTime fechaHoraInicio;
    private int legajoEmpleado;

}
