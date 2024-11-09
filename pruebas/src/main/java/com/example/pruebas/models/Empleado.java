package com.example.pruebas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Column(name = "NOMBRE", nullable = false, length = 30)
    @Size(max = 30, message = "El Nombre no puede superar los 30 caracteres")
    private String nombre;
    @Column(name = "APELLIDO", nullable = false, length = 50)
    @Size(max = 50, message = "El Apellido no puede superar los 50 caracteres")
    private String apellido;

    @Column(name = "TELEFONO_CONTACTO", nullable = false)
    private int telefonoContacto;

    @Column(name = "EMAIL")
    private String email;

    // ------- Relacion: Empleado > Prueba -----------
    // Un empleado puede estar asociado a muchas pruebas (supervisar varias)
    @OneToMany(mappedBy = "empleado" , cascade =  CascadeType.ALL)
    @JsonManagedReference
    private List<Prueba> pruebas;

}
