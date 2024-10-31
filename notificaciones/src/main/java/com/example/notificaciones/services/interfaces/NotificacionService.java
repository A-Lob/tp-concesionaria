package com.example.notificaciones.services.interfaces;

import com.example.notificaciones.models.Notificacion;

public interface NotificacionService {
    void add(Notificacion notificacion);
    public void sendNotification(String email, String asunto, String contenido);
}
