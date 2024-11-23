package com.example.pruebas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "promociones")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TIPO")
    private String tipo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "interesado_promocion",
            joinColumns = @JoinColumn(name = "promocion_ID"),
            inverseJoinColumns = @JoinColumn(name = "interesado_ID")
    )
    @JsonManagedReference
    private List<Interesado> interesados;

    @OneToMany
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Vehiculo> vehiculos;
}

