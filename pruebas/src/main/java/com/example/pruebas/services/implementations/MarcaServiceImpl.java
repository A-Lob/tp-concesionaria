package com.example.pruebas.services.implementations;

import com.example.pruebas.models.Marca;
import com.example.pruebas.repositories.MarcaRepository;
import com.example.pruebas.services.interfaces.MarcaService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarcaServiceImpl extends  ServiceImpl<Marca, Integer> implements MarcaService {
    private MarcaRepository marcaRepository;

    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Override
    public void add(Marca marca) {

    }

    @Override
    public void update(Marca marca) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Marca findById(Integer id) {
        return null;
    }

    @Override
    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }
}
