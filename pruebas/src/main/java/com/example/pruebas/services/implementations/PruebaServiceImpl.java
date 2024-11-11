package com.example.pruebas.services.implementations;

import com.example.pruebas.dtos.NotificacionDTO;
import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.dtos.PromocionDTO;
import com.example.pruebas.models.*;
import com.example.pruebas.repositories.InteresadoRepository;
import com.example.pruebas.repositories.PruebaRepository;
import com.example.pruebas.repositories.VehiculoRepository;
import com.example.pruebas.services.interfaces.PruebaService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PruebaServiceImpl extends ServiceImpl<Prueba, Integer> implements PruebaService {

    private final PruebaRepository pruebaRepository;
    private final InteresadoRepository interesadoRepository;
    private final VehiculoRepository vehiculoRepository;

    public PruebaServiceImpl(PruebaRepository pruebaRepository, InteresadoRepository interesadoRepository, VehiculoRepository vehiculoRepository) {
        this.pruebaRepository = pruebaRepository;
        this.interesadoRepository = interesadoRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public void add(Prueba nuevaPrueba) {

        // Valido si el interesado tiene restricciones o bien si la licencia esta vencida
        if (nuevaPrueba.getInteresado().isRestringido() || nuevaPrueba.getInteresado().getFechaVencimientoLicencia().isBefore(LocalDate.now())) {
            throw new RuntimeException ("El interesado tiene restricciones o la licencia esta vencida");
        }

        // Valido que el vehiculo no este en alguna prueba y si esta que la prueba haya finalizado
        if (pruebaRepository.findByVehiculoAndFechaHoraFinIsNull(nuevaPrueba.getVehiculo()) != null) {
            throw new RuntimeException("El vehiculo esta siendo utilizado en otra prueba");
        }

        // Si no hay condiciones adversas, se procede a registrar la prueba.
        nuevaPrueba.setFechaHoraInicio(LocalDateTime.now());
        this.pruebaRepository.save(nuevaPrueba);
    }

    @Override
    public void update(Prueba prueba) {
        this.pruebaRepository.save(prueba);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Prueba findById(Integer id) {
        return this.pruebaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Prueba> findAll() {
        return this.pruebaRepository.findAll();
    }


    @Override
    public List<Prueba> findPruebasByFechaHora(LocalDateTime fechaHora) {
        return this.pruebaRepository.findByFechaHoraInicioBeforeAndFechaHoraFinIsNull(fechaHora);
    }

    @Override
    public Prueba findPruebaFin(int id) {
        return this.pruebaRepository.findByIdAndFechaHoraFinIsNull(id);
    }

    @Override
    public void controlarVehiculo(PosicionDTO posicion) {
        // Obtengo el vehiculo que esta siendo evaluado
        Vehiculo vehiculo = vehiculoRepository.findById(posicion.getIdVehiculo());

        // Valido que el vehiculo este en alguna prueba en curso
        Prueba prueba = this.pruebaRepository.findByVehiculoAndFechaHoraFinIsNull(vehiculo);
        if (prueba == null) {
            throw new RuntimeException("El vehiculo no se encuentra en ninguna prueba");
        }

        // Calculo la distancia del vehiculo respecto de la ubicacion de la agencia
        double latitudAgencia = -34.603722; // valor random
        double longitudAgencia = -58.381592; // valor random
        double distancia = Math.sqrt(Math.pow(posicion.getLatitud() - latitudAgencia, 2) +
                Math.pow(posicion.getLongitud() - longitudAgencia, 2));
        double radioPermitido = 0.05;
        double zonaPeligrosa = 689; // Se debe realizar un calculo tambien para zonas peligrosas

        // Si excede los limites permitidos se debe enviar una notificacion.
        if (radioPermitido - distancia < 0) {
            // Debo enviar una notificacion al empleado que supervisa la prueba para advertir la situacion
            generarNotificacion(prueba.getEmpleado().getEmail(),
                    "Alerta",
                    "El vehiculo con patente: " + vehiculo.getPatente()
                            + " que inicio una prueba en el dia y hora: " + prueba.getFechaHoraInicio()
                            + " excedio los limites permitidos, el vehiculo debe regresar de inmediato");

            // Se establece al interesado como restringido para que no pueda relizar mas pruebas
            Interesado interesado = prueba.getInteresado();
            interesado.setRestringido(true);
            interesadoRepository.save(interesado);
        }

        // Todavia nose las listas de zonas peligrosas
        if (zonaPeligrosa - distancia < 0) {
            throw new RuntimeException("El vehiculo se encuentra en una zona peligrosa"); // Aca tambien notificar
        }
    }

    @Override
    public void enviarPromociones(PromocionDTO promocion) {
        // Obtengo los interesados registrados en el sistema
        List<Interesado> interesados = interesadoRepository.findAll();
        if (interesados.isEmpty()) {
            new RuntimeException("No hay interesados en pruebas");
        } else {
            interesados.forEach(interesado -> {
                generarNotificacion(
                        interesado.getEmail(), promocion.getAsunto(), promocion.getContenido());
            });
        }
    }


    // Se debe enviar una peticion al microservicio de notificaciones para que procese la informacion,
    // genere la notificacion y la envie a quien corresponda
    public void generarNotificacion(String email, String asunto, String descripcion) {
        // Creación de una instancia de RestTemplate
        try {
            RestTemplate template = new RestTemplate();
            NotificacionDTO notificacion = new NotificacionDTO();
            notificacion.setEmail(email);
            notificacion.setAsunto(asunto);
            notificacion.setDescripcion(descripcion);
            // Creación de la entidad a enviar
            HttpEntity<NotificacionDTO> entity = new HttpEntity<>(notificacion);
            // respuesta de la petición tendrá en su cuerpo a un objeto del tipo
            // notificacion.
            ResponseEntity<NotificacionDTO> res = template.postForEntity("http://localhost:8082/api/notificaciones/enviar-alerta", entity, NotificacionDTO.class
            );
        } catch (HttpClientErrorException exception) {
            // La repuesta no es exitosa.
            exception.printStackTrace();
        }
    }
}
