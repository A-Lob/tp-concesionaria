package com.example.pruebas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Prueba")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Prueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha_hora_inicio")
    private String fechaHoraInicio;

    @Column(name = "fecha_hora_fin")
    private String fechaHoraFin;

    private String comentario;

    // ------- Relacion: Prueba > Empleado -----------
    // Varias pruebas pueden estar asociadas a un solo empleado.
    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "legajo")
    private Empleado empleado;

    // ------- Relacion: Prueba > Interesado -----------
    // Varias pruebas pueden estar asociadas a un solo interesado
    @ManyToOne
    @JoinColumn(name = "id_interesado", referencedColumnName = "id")
    private Interesado interesado;

    // ------- Relacion: Prueba > Vehiculo -----------
    // Varias pruebas pueden estar asociadas a un solo vehículo
    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id")
    private Vehiculo vehiculo;

}
