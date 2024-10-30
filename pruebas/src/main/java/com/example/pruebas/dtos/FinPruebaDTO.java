package com.example.pruebas.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FinPruebaDTO {

    private String comentario;
    private LocalDateTime fechaHoraFin;

}
