package com.example.geolocalizacion.modelos.Auxiliares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinPrueba {
    private String comentario;
    private LocalDateTime fechaHoraFin = LocalDateTime.now();

}
