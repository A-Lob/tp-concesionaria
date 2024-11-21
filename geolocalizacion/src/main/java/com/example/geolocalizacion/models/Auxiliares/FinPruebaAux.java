package com.example.geolocalizacion.models.Auxiliares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinPruebaAux {
    private String comentario;
    private LocalDateTime fechaHoraFin = LocalDateTime.now();

}
