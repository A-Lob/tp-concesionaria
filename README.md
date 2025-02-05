# Concesionario de Automóviles 🚗

## Descripción

Este proyecto es un sistema backend para la gestión de una concesionaria de automóviles, desarrollado como parte de un caso de estudio académico. El sistema está basado en una **arquitectura de microservicios**, lo que permite escalabilidad, modularidad y facilidad de mantenimiento.

El sistema permite gestionar:
- **Vehículos**: Registro, actualización y consulta de automóviles.
- **Interesados**: Gestión de información de interesados.
- **Pruebas** : Registro, actualización, consulta de las pruebas de manejo realizadas y en curso.
- **Empleados**: Gestión de información de empleados.
- **Notificaciones**: Gestión de notificaciones de Promociones o alertas en pruebas.

Puedes ver el caso de estudio [aquí](enunciado.pdf).

En resumen en el caso se nos solicita desarrollar un backend para esta agencia que da la posibilidad a sus clientes de realizar pruebas de manejo de sus productos.

---

## Estructura del proyecto

El sistema está compuesto por los siguientes microservicios, cada uno con una función específica:

1. **Api-gateway**: Punto de acceso único para todas las solicitudes entrantes. Se encarga de enrutar las peticiones a los microservicios correspondientes.

2. **Geolocalización**:
   - Consume un servicio externo para obtener datos necesarios para comprobaciones en pruebas.
   - Funcionalidades:
     - Obtener latitud y longitud de la agencia.
     - Definir el radio máximo de prueba.
     - Proporcionar un listado de zonas peligrosas.

3. **Pruebas**:
   - Encargado de gestionar las pruebas, interesados, empleados, vehiculos y promociones.
   - Funcionalidades:
     - Programar pruebas de manejo.
     - Gestionar pruebas, interesados, empleados, vehiculos y promociones.

4. **Notificaciones**:
   - Encargado de enviar notificaciones vía correo electrónico.
   - Funcionalidades:
     - Notificar sobre el estado de las pruebas a los empleados y las promociones a los clientes.

---

## Tecnologías utilizadas

- **Java**: Lenguaje principal del proyecto.
- **Spring Boot**: Framework para el desarrollo de microservicios.
- **SQLite**: Base de datos ligera para almacenamiento local.
- **Maven**: Gestión de dependencias y construcción del proyecto.
- **Git**: Control de versiones.
- **SLF4J**: Logging para monitoreo y depuración.
- **Swagger**: Documentación interactiva de la API.

---

## Contacto

Si tienes preguntas, sugerencias o simplemente quieres saludar, no dudes en contactarme:

- **Email**: [alex.lobaiza3@gmail.com](mailto:alex.lobaiza3@gmail.com)
