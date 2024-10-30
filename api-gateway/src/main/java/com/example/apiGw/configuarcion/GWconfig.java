package com.example.apiGw.configuarcion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;


@Configuration
public class GWconfig {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${api-gateway.url-microservicio-pruebas}") String uriPruebas,
                                        @Value("${api-gateway.url-microservicio-notificaciones") String uriNotificaciones){
        return builder.routes()
                .route(serv -> serv.path("/api/pruebas/**").uri(uriPruebas))
                .route(serv -> serv.path("/api/notificaciones/**").uri(uriNotificaciones)).build();
    }
}
