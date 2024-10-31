package com.example.notificaciones.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Notificaciones")
@Data
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Destinatario de la notificacion
    private String email;

    // Razon del envio de la notificacion
    private String asunto;

    // Lo que se envia en cada notificacion
    private String contenido;

}
