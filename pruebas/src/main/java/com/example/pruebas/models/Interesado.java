package com.example.pruebas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Interesados")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Interesado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tipo_documento")
    private int tipoDocumento;

    private String documento;

    private String nombre;

    private String apellido;

    private boolean restringido;

    @Column(name = "numero_licencia")
    private int numeroLicencia;

    @Column(name = "fecha_vencimiento_licencia")
    private String fechaVencimientoLicencia;

    // Un interesado puede realizar muchas pruebas.
    @OneToMany(mappedBy = "interesado")
    private List<Prueba> pruebas;

}
