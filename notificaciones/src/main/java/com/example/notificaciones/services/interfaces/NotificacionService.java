package com.example.notificaciones.services.interfaces;

import com.example.notificaciones.models.Notificacion;
import jakarta.mail.MessagingException;

import java.util.Map;

public interface NotificacionService {
    void add(Notificacion notificacion);
    void sendNotification(Notificacion notificacion, Map<String, Object> templateModel , String template) throws MessagingException;
}
