package com.example.pruebas.services.implementations;

import com.example.pruebas.models.*;
import com.example.pruebas.repositories.EmpleadoRepository;
import com.example.pruebas.repositories.InteresadoRepository;
import com.example.pruebas.repositories.PruebaRepository;
import com.example.pruebas.repositories.VehiculoRepository;
import com.example.pruebas.services.interfaces.PruebaService;
import io.micrometer.core.instrument.observation.DefaultMeterObservationHandler;
import jakarta.xml.bind.ValidationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PruebaServiceImpl extends ServiceImpl<Prueba, Integer> implements PruebaService {

    private final PruebaRepository pruebaRepository;
    private final InteresadoRepository interesadoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final DefaultMeterObservationHandler defaultMeterObservationHandler;

    public PruebaServiceImpl(PruebaRepository pruebaRepository, InteresadoRepository interesadoRepository, VehiculoRepository vehiculoRepository, EmpleadoRepository empleadoRepository, DefaultMeterObservationHandler defaultMeterObservationHandler) {
        this.pruebaRepository = pruebaRepository;
        this.interesadoRepository = interesadoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.empleadoRepository = empleadoRepository;
        this.defaultMeterObservationHandler = defaultMeterObservationHandler;
    }

    @Override
    public void add(Prueba prueba) throws ValidationException {
        // Obtengo el interesado de hacer la prueba
        Interesado interesado = prueba.getInteresado();

        // Valido si el interesado tiene restricciones o bien si la licencia esta vencida
        if (interesado.isRestringido() || interesado.getFechaVencimientoLicencia().isBefore(LocalDate.now())) {
            throw new ValidationException("El interesado tiene restricciones o la licencia esta vencida");
        }

        // Valido que el vehiculo solicitado no este en una prueba en curso
        List<Prueba> pruebas = pruebaRepository.findByVehiculoAndFechaHoraFinIsNull(prueba.getVehiculo());
        if (!pruebas.isEmpty()) {
            throw new ValidationException("El vehiculo esta siendo utilizado en otra prueba");
        }
        // Si no hay condiciones adversas, se procede a registrar la prueba.
        this.pruebaRepository.save(prueba);
    }

    @Override
    public void update(Prueba prueba) {
        this.pruebaRepository.save(prueba);
    }

    @Override
    public void delete(Integer id) {
        Prueba prueba = this.pruebaRepository.findById(id).orElseThrow();
        this.pruebaRepository.delete(prueba);
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
    public Interesado AssignInteresadoToPrueba(int id) {
        return this.interesadoRepository.findById(id);
    }

    @Override
    public Vehiculo AssignVehiculoToPrueba(int id) {
        return this.vehiculoRepository.findById(id);
    }

    @Override
    public Empleado AssignEmpleadoToPrueba(int legajo) {
        return this.empleadoRepository.findByLegajo(legajo);
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
    public void controlarVehiculo(Vehiculo vehiculo) {
        // Valido primero que el vehiculo este en alguna prueba
        if (pruebaRepository.findByVehiculoAndFechaHoraFinIsNull(vehiculo).isEmpty()) {
            throw new RuntimeException("Vehiculo no esta en ninguna prueba");
        }
        // Si esta en alguna prueba, entonces obtengo su posicion actual
        Posicion posicionActual = vehiculo.getPosiciones().get(vehiculo.getPosiciones().size() - 1);

        // Calculo la distancia del vehiculo respecto de la ubicacion de la agencia
        double latitudAgencia = 125.3796; // valor random
        double longitudAgencia = 22.4379; // valor random
        double distancia = Math.sqrt(Math.pow(posicionActual.getLatitud() - latitudAgencia, 2) +
                Math.pow(posicionActual.getLongitud() - longitudAgencia, 2));
        double radioPermitido = 55;
        double zonaPeligrosa = 689; // Se debe realizar un calculo tambien para zonas peligrosas

        // Si excede los limites permitidos se debe enviar una notificacion.
        if (radioPermitido - distancia < 0) {
            throw new RuntimeException("El vehiculo se encuentra fuera de los limites"); // Se deberia enviar la notificacion
        }

        if (zonaPeligrosa - distancia < 0) {
            throw new RuntimeException("El vehiculo se encuentra en una zona peligrosa"); // Aca tambien notificar
        }
    }


}
