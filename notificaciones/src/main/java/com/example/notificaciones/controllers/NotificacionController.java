package com.example.notificaciones.controllers;

import com.example.notificaciones.models.Notificacion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    // Creacion de una nueva notifiacion
    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(@RequestBody NotificacionDTO notificacion) {

    }

}
