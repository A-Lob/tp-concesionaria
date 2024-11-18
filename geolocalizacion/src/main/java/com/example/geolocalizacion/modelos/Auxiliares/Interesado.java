package com.example.geolocalizacion.modelos.Auxiliares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interesado {

    private String tipoDocumento;
    private int numDocumento;
    private String nombre;
    private String apellido;
    private LocalDateTime fechaVencimientoLicencia;
    private int numeroLicencia;
    private String email;
    private boolean restringido;

}
