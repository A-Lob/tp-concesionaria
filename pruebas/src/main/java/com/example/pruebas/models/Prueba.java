package com.example.pruebas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Pruebas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Prueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FECHA_HORA_INICIO")
    private String fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private String fechaHoraFin;
    @Column(name = "COMENTARIOS")
    private String comentario;

    // ------- Relacion: Prueba > Empleado -----------
    // Varias pruebas pueden estar asociadas a un solo empleado.
    @ManyToOne
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "LEGAJO")
    private Empleado empleado;

    // ------- Relacion: Prueba > Interesado -----------
    // Varias pruebas pueden estar asociadas a un solo interesado
    @ManyToOne
    @JoinColumn(name = "ID_INTERESADO", referencedColumnName = "ID")
    private Interesado interesado;

    // ------- Relacion: Prueba > Vehiculo -----------
    // Varias pruebas pueden estar asociadas a un solo vehículo
    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID")
    private Vehiculo vehiculo;

}
