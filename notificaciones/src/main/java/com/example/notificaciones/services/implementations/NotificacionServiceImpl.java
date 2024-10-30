package com.example.notificaciones.services.implementations;

import com.example.notificaciones.models.Notificacion;
import com.example.notificaciones.repositories.NotificacionRepository;
import com.example.notificaciones.services.interfaces.NotificacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionServiceImpl extends ServiceImpl<Notificacion, Integer> implements NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public NotificacionServiceImpl(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    @Override
    public void add(Notificacion notificacion) {
        this.notificacionRepository.save(notificacion);
    }


    @Override
    public void update(Notificacion notificacion) {
        this.notificacionRepository.save(notificacion);
    }

    @Override
    public void delete(Integer id) {
        Notificacion notificacion = this.notificacionRepository.findById(id).orElseThrow();
    }

    @Override
    public Notificacion findById(Integer id) {
        return this.notificacionRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Notificacion> findAll() {
        return this.notificacionRepository.findAll();
    }
}
