package com.example.pruebas.services.implementations;

import com.example.pruebas.dtos.MarcaDTO;
import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.dtos.detallesDto.DetalleMarcaDTO;
import com.example.pruebas.dtos.gestorDTOS.GestorDTOS;
import com.example.pruebas.models.Marca;
import com.example.pruebas.services.interfaces.MarcaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MarcaServiceImpl extends ServiceImpl<Marca, Integer> implements MarcaService {
    private final GestorDTOS gestorDTOS;

    public MarcaServiceImpl(GestorDTOS gestorDTOS) {

        this.gestorDTOS = gestorDTOS;
    }

    @Override
    public void add(Marca marca) {
        gestorDTOS.getMarcaRepository().save(marca);

    }

    @Override
    public void update(Marca marca) {
        gestorDTOS.getMarcaRepository().save(marca);
    }

    @Override
    public void delete(Integer id) {
        gestorDTOS.getMarcaRepository().deleteById(id);

    }

    @Override
    public Marca findById(Integer id) {
        return gestorDTOS.getMarcaRepository().findById(id).orElseThrow();
    }

    @Override
    public List<Marca> findAll() {
        return gestorDTOS.getMarcaRepository().findAll();
    }


    public List<DetalleMarcaDTO> marcasAll() {
        log.info("Listando detalles de todas las marcas");
        List<Marca> marcas = gestorDTOS.getMarcaRepository().findAll();
        return marcas.stream().map(m -> {
            DetalleMarcaDTO detalleMarcaDTO = new DetalleMarcaDTO();
            MarcaDTO marcaDTO = new MarcaDTO();

            marcaDTO.setNombre(m.getNombre());

            List<ModeloDTO> modelos = gestorDTOS.listarModelos(m);

            detalleMarcaDTO.setMarca(marcaDTO);
            detalleMarcaDTO.setModelos(modelos);
            return detalleMarcaDTO;
        }).toList();

    }

    public DetalleMarcaDTO obtenerDetalleMarca(int id) {
        log.info("Listando detalles de la marca");
        Marca marca = findById(id);
        DetalleMarcaDTO detalleMarcaDTO = new DetalleMarcaDTO();

        MarcaDTO marcaDTO = new MarcaDTO(marca.getNombre());
        List<ModeloDTO> modeloDTOS = gestorDTOS.listarModelos(marca);

        detalleMarcaDTO.setMarca(marcaDTO);
        detalleMarcaDTO.setModelos(modeloDTOS);

        return detalleMarcaDTO;
    }

    public void eliminar(int id) {
        delete(id);
    }

    public void modificar(MarcaDTO marcaDTO, int id) {
        Marca marca = findById(id);

        marca.setNombre(marcaDTO.getNombre());
        update(marca);
    }

    public void agregar(MarcaDTO marcaDTO) {
        Marca marca = new Marca();

        marca.setNombre(marcaDTO.getNombre());
        add(marca);


    }
}
