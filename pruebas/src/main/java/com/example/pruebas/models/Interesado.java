package com.example.pruebas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @Column(name = "TIPO_DOCUMENTO", nullable = false, length = 3)
    @Size(max = 3, message = "El tipo de Documento no debe superar los 3 caracteres")
    private String tipoDocumento = "DNI";

    @Column(name = "DOCUMENTO", nullable = false, length = 10)
    @Size(max = 10, message = "El Documento no puede superar los 10 caracteres")
    private String documento;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    @Size(max = 50, message = "El Nombre no puede superar los 50 caracteres")
    private String nombre;

    @Column(name = "APELLIDO", nullable = false, length = 50)
    @Size(max = 50, message = "El Apellido no puede superar los 50 caracteres")
    private String apellido;

    @Column(name = "RESTRINGIDO")
    private boolean restringido = false;

    @Column(name = "NRO_LICENCIA", nullable = false)
    private int numeroLicencia;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA", nullable = false)
    private LocalDateTime fechaVencimientoLicencia;

    @Column(name = "EMAIL", nullable = false)
    @Size(max = 255, message = "El Mail no puede superar los 255 caracteres")
    private String email;

    // Un interesado puede realizar muchas pruebas.
    @OneToMany(mappedBy = "interesado", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Prueba> pruebas;

    @ManyToMany(mappedBy = "interesados")
    @JsonIgnore
    private List<Promocion> promociones;

}
