package com.example.pruebas.models;

import jakarta.persistence.*;
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
    private int id;

    private String nombre;

    // ------- Relacion: Marca > Modelo -----------
    // Una marca puede estar asociada a varios modelos.
    @OneToMany(mappedBy = "marca")
    private List<Modelo> modelos;

}
