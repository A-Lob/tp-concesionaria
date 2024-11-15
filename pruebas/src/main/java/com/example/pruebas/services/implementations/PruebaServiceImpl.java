package com.example.pruebas.services.implementations;

import com.example.pruebas.dtos.*;
import com.example.pruebas.dtos.detallesDto.DetallePromocionDTO;
import com.example.pruebas.dtos.detallesDto.DetalleVehiculoDTO;
import com.example.pruebas.dtos.gestorDTOS.GestorDTOS;
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
import java.util.stream.Collectors;

@Service
public class PruebaServiceImpl extends ServiceImpl<Prueba, Integer> implements PruebaService {


    private final GestorDTOS gestorDTOS;

    public PruebaServiceImpl(GestorDTOS gestorDTOS) {
        this.gestorDTOS = gestorDTOS;
    }

    @Override
    public void add(Prueba nuevaPrueba) {
        gestorDTOS.getPruebaRepository().save(nuevaPrueba);

    }

    @Override
    public void update(Prueba prueba) {
        this.gestorDTOS.getPruebaRepository().save(prueba);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Prueba findById(Integer id) {
        return this.gestorDTOS.getPruebaRepository().findById(id).orElseThrow();
    }

    @Override
    public List<Prueba> findAll() {
        return this.gestorDTOS.getPruebaRepository().findAll();
    }


    @Override
    public List<Prueba> findPruebasByFechaHora(LocalDateTime fechaHora) {
        return this.gestorDTOS.getPruebaRepository().findByFechaHoraInicioBeforeAndFechaHoraFinIsNull(fechaHora);
    }

    @Override
    public Prueba findPruebaFin(int id) {
        return this.gestorDTOS.getPruebaRepository().findByIdAndFechaHoraFinIsNull(id);
    }

    // SE HACE CON EL SERVICIO TAMBIEN

    /*




     */
    @Override
    public void controlarVehiculo(PosicionDTO posicion) {
        // Obtengo el vehiculo que esta siendo evaluado
        Vehiculo vehiculo =gestorDTOS.getVehiculoRepository().findById(posicion.getIdVehiculo());

        // Valido que el vehiculo este en alguna prueba en curso
        Prueba prueba = this.gestorDTOS.getPruebaRepository().findByVehiculoAndFechaHoraFinIsNull(vehiculo);
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
            this.gestorDTOS.getInteresadoRepository().save(interesado);
        }

        // Todavia nose las listas de zonas peligrosas
        if (zonaPeligrosa - distancia < 0) {
            throw new RuntimeException("El vehiculo se encuentra en una zona peligrosa"); // Aca tambien notificar
        }
    }

    @Override
    public void enviarPromociones(DetallePromocionDTO promocion) {
        // Obtengo los interesados registrados en el sistema
        List<InteresadoDTO> interesados = promocion.getInteresados();
        if (interesados.isEmpty()) {
            new RuntimeException("No hay interesados en pruebas");
        } else {
            interesados.forEach(interesado -> {
                generarNotificacion(
                        interesado.getEmail(), promocion.getPromocion().getTipo(), promocion.getVehiculos().stream()
                                .map(DetalleVehiculoDTO -> DetalleVehiculoDTO.getModelo().getDescripcion())
                                .collect(Collectors.joining(", \n"))
                );
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

    // Valido si el interesado tiene restricciones o bien si la licencia esta vencida
    // Valido que el vehiculo no este en alguna prueba y si esta que la prueba haya finalizado
    // Si no hay condiciones adversas, se procede a registrar la prueba.

    //LOS VALIDADORES DEVUELVEN TRUE SI PASO LA VALIDACION
    private boolean validadorID(PruebaDTO pruebaDTO){
        List<Vehiculo> vehiculos = gestorDTOS.getVehiculoRepository().findAll();
        List<Interesado> interesados = gestorDTOS.getInteresadoRepository().findAll();
        List<Empleado> empleados = gestorDTOS.getEmpleadoRepository().findAll();

        if(!vehiculos.stream().anyMatch(v -> v.getId() == pruebaDTO.getIdVehiculo())){
            return false;
        }
        if(!interesados.stream().anyMatch(i -> i.getId() == pruebaDTO.getIdInteresado())){
            return false;
        }
        if(!empleados.stream().anyMatch(e -> e.getLegajo() == pruebaDTO.getLegajoEmpleado())){
            return false;
        }
        return true;

    }
    private boolean validorRestringcion(PruebaDTO pruebaDTO){
        Interesado interesado = gestorDTOS.getInteresadoRepository().findById(pruebaDTO.getIdInteresado());
        if(LocalDateTime.now().isAfter(interesado.getFechaVencimientoLicencia()) || interesado.isRestringido()){
            return false;
        }
        return true;
    }
    private boolean validadorPrueba(PruebaDTO pruebaDTO){
        List<Prueba> pruebas = findAll();
        Vehiculo vehiculo = gestorDTOS.getVehiculoRepository().findById(pruebaDTO.getIdVehiculo());
        List<Prueba> coicidencias = pruebas.stream().filter(p ->vehiculo.getId() == p.getVehiculo().getId()).toList();
        if(coicidencias.isEmpty()){
            return true;
        }
        if(coicidencias.stream().allMatch(c ->c.getFechaHoraFin().isAfter(LocalDateTime.now()) )){
            return false;
        }
        return true;


    }
    public void agregar(PruebaDTO pruebaDTO){
        if(!validadorID(pruebaDTO)){
            new RuntimeException("NO EXISTE ALGUNA DE LAS ASIGNACIONES");
        }
        if(!validorRestringcion(pruebaDTO)){
            new RuntimeException("LA LICENCIA DEL INTERESADO ESTA RESTRINGIDA O VENCIDA");
        }

        if(!validadorPrueba(pruebaDTO)){
            new RuntimeException("EL VEHICULO ESTA EN UNA PRUEBA");
        }

        Prueba prueba = new Prueba();
        Empleado empleado = gestorDTOS.getEmpleadoRepository().findByLegajo(pruebaDTO.getLegajoEmpleado());
        Interesado interesado = gestorDTOS.getInteresadoRepository().findById(pruebaDTO.getIdInteresado());
        Vehiculo vehiculo = gestorDTOS.getVehiculoRepository().findById(pruebaDTO.getIdVehiculo());

        prueba.setVehiculo(vehiculo);
        prueba.setInteresado(interesado);
        prueba.setEmpleado(empleado);
        prueba.setComentario("");

        add(prueba);

    }
}
