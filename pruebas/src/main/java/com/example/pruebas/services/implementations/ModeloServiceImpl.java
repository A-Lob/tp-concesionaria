package com.example.pruebas.services.implementations;

import com.example.pruebas.dtos.MarcaDTO;
import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.dtos.VehiculoDTO;
import com.example.pruebas.dtos.detallesDto.DetalleModeloDTO;
import com.example.pruebas.dtos.gestorDTOS.GestorDTOS;
import com.example.pruebas.models.Modelo;
import com.example.pruebas.models.Vehiculo;
import com.example.pruebas.services.interfaces.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloServiceImpl extends ServiceImpl<Modelo, Integer> implements ModeloService {

    private GestorDTOS gestorDTOS;


    public ModeloServiceImpl(GestorDTOS gestorDTOS) {
        this.gestorDTOS = gestorDTOS;
    }

    @Override
    public void add(Modelo modelo) {

    }

    @Override
    public void update(Modelo modelo) {

    }

    @Override
    public void delete(Integer id) {

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
}
