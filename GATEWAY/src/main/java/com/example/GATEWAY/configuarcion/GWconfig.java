package com.example.GATEWAY.configuarcion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GWconfig {
    //FALTAN RUTAS
    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder){
        return builder.routes().build();
    }
}
