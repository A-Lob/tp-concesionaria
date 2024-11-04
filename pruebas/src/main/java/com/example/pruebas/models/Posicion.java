package com.example.pruebas.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Posiciones")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Posicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name = "FECHA_HORA")
    private LocalDateTime fechaHora = LocalDateTime.now();

    @Column(name = "LATITUD", nullable = false)
    private float latitud;

    @Column(name="LONGITUD", nullable = false)
    private float longitud;

    // ------- Relacion: Posicion > Vehiculo -----------
    // Muchas posiciones están asociadas a un solo vehículo
    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID", nullable = false)
    @JsonBackReference
    private Vehiculo vehiculo;

}
