package com.example.pruebas.services.implementations;

import com.example.pruebas.dtos.MarcaDTO;
import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.dtos.VehiculoDTO;
import com.example.pruebas.dtos.detallesDto.DetalleModeloDTO;
import com.example.pruebas.dtos.gestorDTOS.GestorDTOS;
import com.example.pruebas.models.Marca;
import com.example.pruebas.models.Modelo;
import com.example.pruebas.models.Vehiculo;
import com.example.pruebas.services.interfaces.ModeloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ModeloServiceImpl extends ServiceImpl<Modelo, Integer> implements ModeloService {

    private GestorDTOS gestorDTOS;


    public ModeloServiceImpl(GestorDTOS gestorDTOS) {
        this.gestorDTOS = gestorDTOS;
    }

    @Override
    public void add(Modelo modelo) {
        gestorDTOS.getModeloRepository().save(modelo);
    }

    @Override
    public void update(Modelo modelo) {
        gestorDTOS.getModeloRepository().save(modelo);
    }

    @Override
    public void delete(Integer id) {
        gestorDTOS.getModeloRepository().deleteById(id);
    }

    @Override
    public Modelo findById(Integer id) {
        return gestorDTOS.getModeloRepository().findById(id).orElseThrow();
    }

    @Override
    public List<Modelo> findAll() {
        return gestorDTOS.getModeloRepository().findAll();
    }

    public List<DetalleModeloDTO> modelosAll(){
        log.info("Listando detalles de todas las marcas");
        List<Modelo> modelos = gestorDTOS.getModeloRepository().findAll();
        List<DetalleModeloDTO> modeloDTOS =  modelos.stream().map(m -> {

            DetalleModeloDTO detalleModeloDTO = new DetalleModeloDTO();
            ModeloDTO modeloDTO = new ModeloDTO(m.getDescripcion());
            MarcaDTO marcaDTO = new MarcaDTO(m.getMarca().getNombre());
            List<VehiculoDTO> listadoVehiculosDtos = gestorDTOS.listaVehiculosDtos(m);

            detalleModeloDTO.setModelo(modeloDTO);
            detalleModeloDTO.setVehiculos(listadoVehiculosDtos);
            detalleModeloDTO.setMarca(marcaDTO);
            return detalleModeloDTO;
        }).toList();

       return modeloDTOS;

    }
    public DetalleModeloDTO obtenerDetalleModelo(int id){
        log.info("Listando detalles de la marca");
        Modelo modelo = findById(id);
        ModeloDTO modeloDTO = new ModeloDTO(modelo.getDescripcion());
        MarcaDTO marcaDTO = new MarcaDTO(modelo.getMarca().getNombre());

        DetalleModeloDTO detalleModeloDTO = new DetalleModeloDTO();

        detalleModeloDTO.setVehiculos(gestorDTOS.listaVehiculosDtos(modelo));
        detalleModeloDTO.setMarca(marcaDTO);
        detalleModeloDTO.setModelo(modeloDTO);

        return detalleModeloDTO;
    }


    public void nuevoModelo(ModeloDTO modeloDTO, int id){
        Modelo modelo = new Modelo();
        Marca marca  = gestorDTOS.getMarcaRepository().findById(id);

        modelo.setMarca(marca);
        modelo.setMarca(marca);
        modelo.setDescripcion(modeloDTO.getDescripcion());
        add(modelo);

    }
    public void eliminarModelo(int id){
        delete(id);
    }
    public void modificar(int id, ModeloDTO modeloDTO){
        Modelo modelo = findById(id);
        modelo.setDescripcion(modeloDTO.getDescripcion());
        update(modelo);
    }
}
