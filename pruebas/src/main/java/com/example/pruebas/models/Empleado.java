package com.example.pruebas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Empleados")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int legajo;

    private String nombre;

    private String apellido;

    @Column(name = "telefono_contacto")
    private int telefonoContacto;

    // ------- Relacion: Empleado > Prueba -----------
    // Un empleado puede estar asociado a muchas pruebas (supervisar varias)
    @OneToMany(mappedBy = "empleado")
    private List<Prueba> pruebas;

}
