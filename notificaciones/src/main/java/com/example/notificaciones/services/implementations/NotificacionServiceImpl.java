package com.example.notificaciones.services.implementations;

import com.example.notificaciones.models.Notificacion;
import com.example.notificaciones.repositories.NotificacionRepository;
import com.example.notificaciones.services.interfaces.NotificacionService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.List;
import java.util.Map;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public NotificacionServiceImpl(NotificacionRepository notificacionRepository,
                                   JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.notificacionRepository = notificacionRepository;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void add(Notificacion notificacion) {
        this.notificacionRepository.save(notificacion);
    }

    @Override
    public void sendNotification(Notificacion notificacion, Map<String, Object> templateModel , String template) throws MessagingException {
        Context context = new Context();
        context.setVariables(templateModel);

        System.out.println(templateModel);

        String htmlContent = templateEngine.process(template, context);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(notificacion.getEmail());
        helper.setSubject(notificacion.getAsunto());
        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);

    }
}
