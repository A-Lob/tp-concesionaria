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
    private int id;

    @Column(name = "fecha_hora")
    private String fechaHora;

    private float latitud;

    private float longitud;

    // ------- Relacion: Posicion > Vehiculo -----------
    // Muchas posiciones están asociadas a un solo vehículo
    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id")
    private Vehiculo vehiculo;

}
