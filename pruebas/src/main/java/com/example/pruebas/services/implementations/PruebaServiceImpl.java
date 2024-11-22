package com.example.pruebas.services.implementations;

import com.example.pruebas.dtos.*;
import com.example.pruebas.dtos.detallesDto.DetallePruebaDTO;
import com.example.pruebas.dtos.gestorDTOS.GestorDTOS;
import com.example.pruebas.models.*;
import com.example.pruebas.services.interfaces.PruebaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
        gestorDTOS.getPruebaRepository().deleteById(id);
    }

    @Override
    public Prueba findById(Integer id) {
        return this.gestorDTOS.getPruebaRepository().findById(id).orElseThrow();
    }

    @Override
    public List<Prueba> findAll() {
        return this.gestorDTOS.getPruebaRepository().findAll();
    }


    // SE HACE CON EL SERVICIO TAMBIEN

    /*




     */
    @Override
    public void controlarVehiculo(PosicionDTO posicion) {
        // Obtengo el vehiculo que esta siendo evaluado
        Vehiculo vehiculo = gestorDTOS.getVehiculoRepository().findById(posicion.getIdVehiculo());

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
                    modelarAsunto(prueba), "alerta-template");

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

    public Map<String, Object> modelarAsunto(Prueba prueba) {
        log.info("Modelando mail");
        Empleado empleado = prueba.getEmpleado();
        Vehiculo vehiculo = prueba.getVehiculo();

        Map<String, Object> model = new HashMap<>();
        model.put("lastName", empleado.getApellido());
        model.put("firstName", empleado.getNombre());
        model.put("employeeId", empleado.getLegajo());
        model.put("vehiclePlate", vehiculo.getPatente());

        LocalDateTime fechaHoraInicio = prueba.getFechaHoraInicio();
        long timestamp = fechaHoraInicio.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Instant instant = Instant.ofEpochMilli(timestamp);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());
        String formattedDateTime = formatter.format(instant);

        model.put("testStartDateTime", formattedDateTime);

        return model;
    }

    public void generarNotificacion(String email, String asunto, Map<String, Object> model, String mailTemplate) {
        log.info("Generando notificacion");
        try {
            RestTemplate template = new RestTemplate();
            NotificacionDTO notificacionDto = new NotificacionDTO();
            notificacionDto.setEmail(email);
            notificacionDto.setAsunto(asunto);
            notificacionDto.setDescripcion("descripcion");
            NotificacionRequest request = new NotificacionRequest(notificacionDto, model, mailTemplate);
            HttpEntity<NotificacionRequest> entity = new HttpEntity<>(request);

            ResponseEntity<NotificacionDTO> res = template.postForEntity("http://localhost:8082/api/notificaciones/enviar-alerta",
                    entity, NotificacionDTO.class
            );
        } catch (HttpClientErrorException exception) {
            exception.printStackTrace();
        }
    }

    // Valido si el interesado tiene restricciones o bien si la licencia esta vencida
    // Valido que el vehiculo no este en alguna prueba y si esta que la prueba haya finalizado
    // Si no hay condiciones adversas, se procede a registrar la prueba.

    //LOS VALIDADORES DEVUELVEN TRUE SI PASO LA VALIDACION
    private boolean validadorID(PruebaDTO pruebaDTO) {
        log.info("Validando Ids de Vehiculo, Interesado y Empleado");
        List<Vehiculo> vehiculos = gestorDTOS.getVehiculoRepository().findAll();
        List<Interesado> interesados = gestorDTOS.getInteresadoRepository().findAll();
        List<Empleado> empleados = gestorDTOS.getEmpleadoRepository().findAll();

        if (!vehiculos.stream().anyMatch(v -> v.getId() == pruebaDTO.getIdVehiculo())) {
            return false;
        }
        if (!interesados.stream().anyMatch(i -> i.getId() == pruebaDTO.getIdInteresado())) {
            return false;
        }
        if (!empleados.stream().anyMatch(e -> e.getLegajo() == pruebaDTO.getLegajoEmpleado())) {
            return false;
        }
        return true;

    }

    private boolean validorRestriccion(PruebaDTO pruebaDTO) {
        log.info("Validando Restriccion");
        Interesado interesado = gestorDTOS.getInteresadoRepository().findById(pruebaDTO.getIdInteresado());
        if (LocalDateTime.now().isAfter(interesado.getFechaVencimientoLicencia()) || interesado.isRestringido()) {
            return false;
        }
        return true;
    }

    private boolean validadorPrueba(PruebaDTO pruebaDTO) {
        log.info("Validando Prueba");
        List<Prueba> pruebas = findAll();
        Vehiculo vehiculo = gestorDTOS.getVehiculoRepository().findById(pruebaDTO.getIdVehiculo());
        List<Prueba> coicidencias = pruebas.stream().filter(p -> vehiculo.getId() == p.getVehiculo().getId()).toList();
        if (coicidencias.isEmpty()) {
            return true;
        }
        if (coicidencias.stream().allMatch(c -> c.getFechaHoraFin().isAfter(LocalDateTime.now()))) {
            return false;
        }
        return true;


    }

    public void agregar(PruebaDTO pruebaDTO) {
        if (!validadorID(pruebaDTO)) {
            new RuntimeException("NO EXISTE ALGUNA DE LAS ASIGNACIONES");
        }
        if (!validorRestriccion(pruebaDTO)) {
            new RuntimeException("LA LICENCIA DEL INTERESADO ESTA RESTRINGIDA O VENCIDA");
        }

        if (!validadorPrueba(pruebaDTO)) {
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

    private DetallePruebaDTO obtenerDetallePruebaDTO(Prueba p) {
        log.info("Listando detalle de la prueba");
        DetallePruebaDTO detallePruebaDTO = new DetallePruebaDTO();
        FinPruebaDTO finPruebaDTO = new FinPruebaDTO();
        finPruebaDTO.setComentario(p.getComentario());
        finPruebaDTO.setFechaHoraFin(p.getFechaHoraFin());
        detallePruebaDTO.setEmpleado(gestorDTOS.empleadoDTO(p.getEmpleado()));
        detallePruebaDTO.setInteresado(gestorDTOS.interesadoDTO(p.getInteresado()));
        detallePruebaDTO.setVehiculo(gestorDTOS.vehiculoDTO(p.getVehiculo()));
        detallePruebaDTO.setPrueba(gestorDTOS.pruebaDTO(p));
        detallePruebaDTO.setFinPrueba(finPruebaDTO);
        return detallePruebaDTO;
    }

    public List<DetallePruebaDTO> TodosPorFechas(String fechaStr, String horaStr) {
        log.info("Listando todas las pruebas por fecha");
        // Convertir fecha y hora de String a LocalDate y LocalTime
        DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato según tu necesidad


        LocalDate fecha = LocalDate.parse(fechaStr, fechaFormatter);
        int hora = Integer.parseInt(horaStr); // Convertir la hora a entero

        List<Prueba> pruebas = findAll();

        // Filtrar las fechas que coincidan en la fecha y hora (sin minutos y segundos)
        List<Prueba> pruebaFiltradas = pruebas.stream()
                .filter(p ->
                        p.getFechaHoraInicio().toLocalDate().equals(fecha) &&
                                p.getFechaHoraInicio().getHour() == hora
                )
                .toList();
        if (pruebaFiltradas.isEmpty()) {
            List<DetallePruebaDTO> mal = new ArrayList();
            return mal;
        }

        return pruebaFiltradas.stream().map(this::obtenerDetallePruebaDTO).toList();
    }

    public List<DetallePruebaDTO> todas() {
        List<Prueba> pruebas = findAll();
        return pruebas.stream().map(this::obtenerDetallePruebaDTO).toList();

    }

    public void finalizarPrueba(int id, FinPruebaDTO finPruebaDTO){
        Prueba prueba = findById(id);
        prueba.setComentario(finPruebaDTO.getComentario());
        prueba.setFechaHoraFin(finPruebaDTO.getFechaHoraFin());

        update(prueba);
    }

    public void eliminarPrueba(int id){
        delete(id);
    }

}
