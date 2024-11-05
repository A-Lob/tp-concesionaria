package com.example.pruebas.services.implementations;

import com.example.pruebas.models.Modelo;
import com.example.pruebas.repositories.ModeloRepository;
import com.example.pruebas.services.interfaces.ModeloService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloServiceImpl extends ServiceImpl<Modelo, Integer> implements ModeloService {
    private ModeloRepository modeloRepository;

    public ModeloServiceImpl(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
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
        return modeloRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Modelo> findAll() {
        return modeloRepository.findAll();
    }
}
