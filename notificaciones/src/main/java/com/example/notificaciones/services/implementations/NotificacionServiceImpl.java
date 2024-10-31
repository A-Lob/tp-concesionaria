package com.example.notificaciones.services.implementations;

import com.example.notificaciones.models.Notificacion;
import com.example.notificaciones.repositories.NotificacionRepository;
import com.example.notificaciones.services.interfaces.NotificacionService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionServiceImpl extends ServiceImpl<Notificacion, Integer> implements NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final JavaMailSender mailSender;

    public NotificacionServiceImpl(NotificacionRepository notificacionRepository, JavaMailSender mailSender) {
        this.notificacionRepository = notificacionRepository;
        this.mailSender = mailSender;
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

    @Override
    public void sendNotification(String email, String asunto, String contenido) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(asunto);
        message.setText(contenido);
        mailSender.send(message);
    }
}
