package com.example.pruebas.services.implementations;

import com.example.pruebas.models.Posicion;
import com.example.pruebas.repositories.PosicionRepository;
import com.example.pruebas.services.interfaces.PosicionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosicionServiceImpl extends ServiceImpl<Posicion, Integer> implements PosicionService {
    private PosicionRepository repository;

    public PosicionServiceImpl(PosicionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Posicion posicion) {
        repository.save(posicion);


    }

    @Override
    public void update(Posicion posicion) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Posicion findById(Integer id) {
        return null;
    }

    @Override
    public List<Posicion> findAll() {
        return repository.findAll();
    }
}
