package com.example.pruebas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Marcas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NOMBRE", nullable = false, length = 30)
    @Size(max = 30, message = "El Nombre no puede superar los 30 caracteres")
    private String nombre;

    // ------- Relacion: Marca > Modelo -----------
    // Una marca puede estar asociada a varios modelos.
    @OneToMany(mappedBy = "marca")
    private List<Modelo> modelos;

}
