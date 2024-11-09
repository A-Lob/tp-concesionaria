package com.example.notificaciones.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Notificaciones")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Destinatario de la notificacion
    @Column(name="MAIL", nullable = false)
    @NotBlank(message = "Campo Mail vacío")
    @Email(message = "Formato de dirección e-mail incorrecto")
    private String email;

    // Razon del envio de la notificacion
    @Column(name="ASUNTO", nullable = false, length = 20)
    @Size(max = 20, message = "El asunto no puede exceder los 20 caracteres")
    @NotBlank(message = "El asunto no debe estar vacío")
    private String asunto = "Vehiculo fuera de radio";

    // Lo que se envia en cada notificacion
    @Column(name="DESCRIPCION", length = 300)
    @Size(max = 300)
    private String descripcion;
}
