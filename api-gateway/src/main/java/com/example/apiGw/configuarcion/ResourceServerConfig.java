package com.example.apiGw.configuarcion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
// Se utiliza Spring WebFlux para implementar la API Gateway. Habilita la configuración de
// seguridad específica para aplicaciones basadas en modelo reactivo.
public class ResourceServerConfig {

    @Bean
    // SecurityWebFilterChain aplica para aplicaciones reactivas, al igual que ServerHttpSecurity
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange(exchanges -> exchanges
                        // únicamente un empleado pueda crear pruebas y enviar notificaciones
                        .pathMatchers("/api/pruebas/nueva-prueba", "/api/notificaciones/enviar-alerta")
                        .hasAnyRole("EMPLEADO")

                        // Solamente un administrador puede ver los datos de los reportes
                        .pathMatchers(("/api/geolocalizacion/reportes/**"))
                        .hasAnyRole("ADMIN")
                        .anyExchange()
                        .authenticated()
                ).oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    // Las aplicaciones reactivas necesitan usar tipos y clases que trabajen de manera no bloqueante, por eso se
    // utiliza ReactiveJwtAuthenticationConverter en lugar de JwtAuthenticationConverter.
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        // JwtGrantedAuthoritiesConverter es bloqueante, pero el adaptador convierte su
        // comportamiento a uno reactivo que no bloquea el flujo de procesamiento.
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new ReactiveJwtGrantedAuthoritiesConverterAdapter(grantedAuthoritiesConverter));
        return jwtAuthenticationConverter;
    }

}
