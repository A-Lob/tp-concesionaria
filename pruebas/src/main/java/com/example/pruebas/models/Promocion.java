package com.example.pruebas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Promociones")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    // Las promociones tienen un tipo como Descuento, Financiamiento especial, Promociones en seguros, etc.
    @Column(name = "TIPO")
    private String tipo;

    @ManyToMany(mappedBy = "promociones")
    @JsonManagedReference
    private List<Interesado> interesados;

    @OneToMany(mappedBy = "promocion")
    @JsonManagedReference
    private List<Vehiculo> vehiculos;
}

