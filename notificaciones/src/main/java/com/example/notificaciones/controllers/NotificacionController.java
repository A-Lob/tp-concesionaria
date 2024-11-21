package com.example.notificaciones.controllers;

import com.example.notificaciones.models.Notificacion;
import com.example.notificaciones.services.interfaces.NotificacionService;
import com.example.pruebas.dtos.NotificacionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    // Creacion de una nueva notificacion de alerta
    @PostMapping("/enviar-alerta")
    public ResponseEntity<Object> crearNotificacion(@RequestBody NotificacionRequest notificacionRequest) {
        try {
            Notificacion notificacion = new Notificacion();
            notificacion.setEmail(notificacionRequest.getNotificacion().getEmail());
            notificacion.setAsunto(notificacionRequest.getNotificacion().getAsunto());
            notificacion.setDescripcion(notificacionRequest.getNotificacion().getDescripcion());
            Map<String, Object> templateModel = notificacionRequest.getTemplateModel();
            String template = notificacionRequest.getTemplate();

            this.notificacionService.add(notificacion);
            this.notificacionService.sendNotification(notificacion, templateModel, template);
            return new ResponseEntity<>(notificacion, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
