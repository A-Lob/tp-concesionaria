package com.example.pruebas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Interesados")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Interesado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento = "DNI";
    @Column(name = "DOCUMENTO")
    private String documento;

    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "RESTRINGIDO")
    private boolean restringido = false;

    @Column(name = "NRO_LICENCIA")
    private int numeroLicencia;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private LocalDate fechaVencimientoLicencia;

    @Column(name = "EMAIL")
    private String email;

    // Un interesado puede realizar muchas pruebas.
    @OneToMany(mappedBy = "interesado")
    @JsonManagedReference
    private List<Prueba> pruebas;

}
