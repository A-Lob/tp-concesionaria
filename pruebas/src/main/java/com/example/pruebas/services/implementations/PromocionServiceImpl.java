package com.example.pruebas.services.implementations;

import com.example.pruebas.dtos.InteresadoDTO;
import com.example.pruebas.dtos.NotificacionDTO;
import com.example.pruebas.dtos.PromocionDTO;
import com.example.pruebas.dtos.detallesDto.DetallePromocionDTO;
import com.example.pruebas.dtos.detallesDto.DetalleVehiculoDTO;
import com.example.pruebas.models.Interesado;
import com.example.pruebas.models.Promocion;
import com.example.pruebas.models.Vehiculo;
import com.example.pruebas.services.interfaces.PromocionService;
import com.example.pruebas.dtos.gestorDTOS.GestorDTOS;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromocionServiceImpl extends ServiceImpl<Promocion, Integer> implements PromocionService {

    private final GestorDTOS gestorDTOS;

    public PromocionServiceImpl(GestorDTOS gestorDTOS) { this.gestorDTOS = gestorDTOS; }

    @Override
    public void add(Promocion promocionNueva) { gestorDTOS.getPromocionRepository().save(promocionNueva); }

    @Override
    public void update(Promocion promocionModificada) { gestorDTOS.getPromocionRepository().save(promocionModificada); }

    @Override
    public void delete(Integer id) { gestorDTOS.getPromocionRepository().deleteById(id); }

    @Override
    public Promocion findById(Integer id) { return gestorDTOS.getPromocionRepository().findById(id).orElseThrow(); }

    @Override
    public List<Promocion> findAll() { return gestorDTOS.getPromocionRepository().findAll(); }

    public List<DetallePromocionDTO> PromocionesAll() {
        List<Promocion> promociones = gestorDTOS.getPromocionRepository().findAll();
        return promociones.stream().map(p -> {
            DetallePromocionDTO detallePromocionDTO = new DetallePromocionDTO();
            PromocionDTO promocionDTO = new PromocionDTO();

            promocionDTO.setTipo(p.getTipo());

            List<InteresadoDTO> interesados = gestorDTOS.listarInteresados(p);
            List<DetalleVehiculoDTO> detalleVehiculosDTOS = gestorDTOS.listarVehiculosPromocion(p);

            detallePromocionDTO.setPromocion(promocionDTO);
            detallePromocionDTO.setInteresados(interesados);
            detallePromocionDTO.setVehiculos(detalleVehiculosDTOS);

            return detallePromocionDTO;
        }).toList();
    }

    public void nuevaPromocion(PromocionDTO promocionDTO, List<Integer> interesadosId, List<Integer> vehiculosId) {
        Promocion promocion = new Promocion();
        List<Interesado> interesados = gestorDTOS.getInteresadoRepository().findAllById(interesadosId);
        List<Vehiculo> vehiculos = gestorDTOS.getVehiculoRepository().findAllById(vehiculosId);

        promocion.setTipo(promocionDTO.getTipo());
        promocion.setInteresados(interesados);
        promocion.setVehiculos(vehiculos);
        add(promocion);
    }

    public DetallePromocionDTO obtenerDetallePromocion(Integer id) {
        Promocion promocion = findById(id);
        PromocionDTO promocionDTO = new PromocionDTO();
        DetallePromocionDTO detallePromocionDTO = new DetallePromocionDTO();
        List<InteresadoDTO> interesadosDtos = gestorDTOS.listarInteresados(promocion);
        List<DetalleVehiculoDTO> DetalleVehiculoDtos = gestorDTOS.listarVehiculosPromocion(promocion);

        promocionDTO.setTipo(promocion.getTipo());
        detallePromocionDTO.setPromocion(promocionDTO);
        detallePromocionDTO.setInteresados(interesadosDtos);
        detallePromocionDTO.setVehiculos(DetalleVehiculoDtos);
        return detallePromocionDTO;
    }

    public void eliminarPromocion(Integer id) { delete(id); }

    public void enviarPromociones(Integer id) {
        DetallePromocionDTO promocion = obtenerDetallePromocion(id);
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

    public void generarNotificacion(String email, String asunto, String descripcion) {
        try {
            RestTemplate template = new RestTemplate();
            NotificacionDTO notificacion = new NotificacionDTO();
            notificacion.setEmail(email);
            notificacion.setAsunto(asunto);
            notificacion.setDescripcion(descripcion);
            HttpEntity<NotificacionDTO> entity = new HttpEntity<>(notificacion);

            ResponseEntity<NotificacionDTO> res = template.postForEntity("http://localhost:8082/api/notificaciones/enviar-alerta", entity, NotificacionDTO.class
            );
        } catch (HttpClientErrorException exception) {
            exception.printStackTrace();
        }
    }

}
