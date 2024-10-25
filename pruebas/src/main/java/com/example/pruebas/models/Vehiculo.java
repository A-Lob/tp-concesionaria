package com.example.pruebas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Vehiculos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String patente;

    private int anio;

    // ------- Relacion: Vehiculo > Prueba -----------
    // Un vehículo puede tener muchas pruebas.
    @OneToMany(mappedBy = "vehiculo")
    private List<Prueba> pruebas;

    // ------- Relacion: Vehiculo > Posicion -----------
    // Un vehículo puede tener muchas posiciones registradas
    @OneToMany(mappedBy = "vehiculo")
    private List<Posicion> posiciones;

    // ------- Relacion: Vehiculo > Modelo -----------
    // Varios vehículos puede estar asociados a un modelo.
    @ManyToOne
    @JoinColumn(name = "id_modelo", referencedColumnName = "id")
    private Modelo modelo;

}
