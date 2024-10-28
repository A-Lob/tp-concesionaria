package com.example.pruebas.dtos;

import com.example.pruebas.models.Interesado;
import com.example.pruebas.models.Vehiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(force = true)
public class PruebaDTO {

    final private Interesado interesado;
    final private Vehiculo vehiculo;
    final private LocalDateTime fechaHoraInicio;
    final private LocalDateTime fechaHoraFin;

    public PruebaDTO(Interesado interesado, Vehiculo vehiculo, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        this.interesado = interesado;
        this.vehiculo = vehiculo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
    }
}
