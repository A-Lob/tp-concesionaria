package com.example.pruebas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionRequest {
    private NotificacionDTO notificacion;
    private Map<String, Object> templateModel;
    private String template;
}
