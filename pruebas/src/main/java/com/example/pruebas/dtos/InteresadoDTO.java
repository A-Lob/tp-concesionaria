package com.example.pruebas.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InteresadoDTO {

    private int id;
    private LocalDate fechaVencimientoLicencia;
    private boolean restringido;
}
