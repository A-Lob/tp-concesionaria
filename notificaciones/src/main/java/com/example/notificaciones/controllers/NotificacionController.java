package com.example.notificaciones.controllers;

import com.example.notificaciones.models.Notificacion;
import com.example.notificaciones.services.interfaces.NotificacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    // Creacion de una nueva notificacion
    @PostMapping("enviar-email")
    public ResponseEntity<Object> crearNotificacion(@RequestBody Notificacion notificacion) {
        try {
            this.notificacionService.add(notificacion);
            this.notificacionService.sendNotification(notificacion.getEmail(), notificacion.getAsunto(), notificacion.getContenido());
            return new ResponseEntity<>(notificacion, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
