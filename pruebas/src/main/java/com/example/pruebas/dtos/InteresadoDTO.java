package com.example.pruebas.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InteresadoDTO {

    private String tipoDocumento;
    private int numDocumento;
    private String nombre;
    private String apellido;
    private LocalDate fechaVencimientoLicencia;
    private int numeroLicencia;

}
