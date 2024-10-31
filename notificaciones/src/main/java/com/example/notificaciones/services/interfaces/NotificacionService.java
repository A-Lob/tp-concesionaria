package com.example.notificaciones.services.interfaces;

import com.example.notificaciones.models.Notificacion;

public interface NotificacionService extends Service<Notificacion, Integer> {
    public void sendNotification(String email, String asunto, String contenido);
}
