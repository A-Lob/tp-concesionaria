package com.example.pruebas.services.implementations;

import com.example.pruebas.dtos.InteresadoDTO;
import com.example.pruebas.dtos.PruebaDTO;
import com.example.pruebas.dtos.detallesDto.DetalleInteresadoDTO;
import com.example.pruebas.dtos.gestorDTOS.GestorDTOS;
import com.example.pruebas.models.Interesado;
import com.example.pruebas.repositories.InteresadoRepository;
import com.example.pruebas.services.interfaces.InteresadoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InteresadoServiceImpl extends ServiceImpl<Interesado, Integer> implements InteresadoService {
    private final GestorDTOS gestorDTOS;

    public InteresadoServiceImpl(GestorDTOS gestorDTOS) {
        this.gestorDTOS = gestorDTOS;
    }

    @Override
    public void add(Interesado interesado) {
        gestorDTOS.getInteresadoRepository().save(interesado);

    }

    @Override
    public void update(Interesado interesado) {
        gestorDTOS.getInteresadoRepository().save(interesado);

    }

    @Override
    public void delete(Integer id) {
        gestorDTOS.getInteresadoRepository().deleteById(id);

    }

    @Override
    public Interesado findById(Integer id) {
        return gestorDTOS.getInteresadoRepository().findById(id).orElseThrow();
    }

    @Override
    public List<Interesado> findAll() {
        return gestorDTOS.getInteresadoRepository().findAll();
    }

    private Interesado cargaInteresado(Interesado interesado, InteresadoDTO interesadoDTO){
        interesado.setTipoDocumento(interesadoDTO.getTipoDocumento());
        interesado.setDocumento(String.valueOf(interesadoDTO.getNumDocumento()));
        interesado.setNombre(interesadoDTO.getNombre());
        interesado.setApellido(interesadoDTO.getApellido());
        interesado.setFechaVencimientoLicencia(interesadoDTO.getFechaVencimientoLicencia());
        interesado.setNumeroLicencia(interesadoDTO.getNumeroLicencia());
        interesado.setRestringido(interesadoDTO.isRestringido());
        interesado.setEmail(interesadoDTO.getEmail());

        return interesado;
    }
    public List<DetalleInteresadoDTO> todos() {
        List<Interesado> interesados = findAll();
        return interesados.stream().map(i -> {

            DetalleInteresadoDTO detalleInteresadoDTO = new DetalleInteresadoDTO();
            InteresadoDTO interesadoDTO = gestorDTOS.interesadoDTO(i);
            List<PruebaDTO> pruebas = gestorDTOS.listarPruebas(i);


            detalleInteresadoDTO.setInteresado(interesadoDTO);
            detalleInteresadoDTO.setPruebas(pruebas);

            return detalleInteresadoDTO;


        }).toList();

    }

    public DetalleInteresadoDTO interesado(int id) {
        Interesado interesado = findById(id);
        DetalleInteresadoDTO detalleInteresadoDTO = new DetalleInteresadoDTO();
        InteresadoDTO interesadoDTO = gestorDTOS.interesadoDTO(interesado);
        List<PruebaDTO> pruebas = gestorDTOS.listarPruebas(interesado);
        detalleInteresadoDTO.setInteresado(interesadoDTO);
        detalleInteresadoDTO.setPruebas(pruebas);
        return detalleInteresadoDTO;


    }

    public void eliminar(int id) {
        delete(id);
    }
    public void agregar(InteresadoDTO interesadoDTO) {
        Interesado interesado = new Interesado();

        Interesado nuevoInteresado = cargaInteresado(interesado, interesadoDTO);
        add(nuevoInteresado);

    }

    public void actualizar (InteresadoDTO interesadoDTO, int id) {
        Interesado interesado = findById(id);
       Interesado interesado1 =  cargaInteresado(interesado, interesadoDTO);
       update(interesado1);



    }
}



