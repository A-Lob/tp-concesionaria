package com.example.pruebas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String fechaHora;

    @Column(name = "LATITUD")
    private float latitud;

    @Column(name="LONGITUD")
    private float longitud;

    // ------- Relacion: Posicion > Vehiculo -----------
    // Muchas posiciones están asociadas a un solo vehículo
    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID")
    private Vehiculo vehiculo;

}
