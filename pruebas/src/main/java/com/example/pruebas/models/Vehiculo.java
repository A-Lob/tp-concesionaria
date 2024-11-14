package com.example.pruebas.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Column(name = "ID")
    private int id;

    @Column(name = "PATENTE", nullable = false)
    private String patente;
    @Column(name = "ANIO", nullable = false, length = 4)
    @Size(max = 4, message = "El año no debe superar los 4 caracteres")
    private int anio = 2019;

    // ------- Relacion: Vehiculo > Prueba -----------
    // Un vehículo puede tener muchas pruebas.
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Prueba> pruebas;

    // ------- Relacion: Vehiculo > Posicion -----------
    // Un vehículo puede tener muchas posiciones registradas
    @OneToMany(mappedBy = "vehiculo")
    @JsonManagedReference
    private List<Posicion> posiciones;

    // ------- Relacion: Vehiculo > Modelo -----------
    // Varios vehículos puede estar asociados a un modelo.
    @ManyToOne
    @JoinColumn(name = "ID_MODELO", referencedColumnName = "ID")
    @JsonBackReference
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "promocion_id")
    @JsonBackReference
    private Promocion promocion;

}
