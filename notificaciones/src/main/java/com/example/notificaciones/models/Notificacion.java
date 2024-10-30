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

    // Lo que se envia en cada notificacion
    private String contenido;

    // Si es via sms, mail o whatsapp
    private String tipoNotificacion;

}
