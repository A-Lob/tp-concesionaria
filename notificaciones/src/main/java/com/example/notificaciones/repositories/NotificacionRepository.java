package com.example.notificaciones.repositories;

import com.example.notificaciones.models.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByEmail(String email);

    List<Notificacion> findByAsuntoContaining(String asunto);
}
