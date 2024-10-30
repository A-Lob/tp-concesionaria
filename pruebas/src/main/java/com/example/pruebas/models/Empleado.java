package com.example.pruebas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "LEGAJO")
    private int legajo;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "TELEFONO_CONTACTO")
    private int telefonoContacto;

    // ------- Relacion: Empleado > Prueba -----------
    // Un empleado puede estar asociado a muchas pruebas (supervisar varias)
    @OneToMany(mappedBy = "empleado")
    @JsonManagedReference
    private List<Prueba> pruebas;

}
