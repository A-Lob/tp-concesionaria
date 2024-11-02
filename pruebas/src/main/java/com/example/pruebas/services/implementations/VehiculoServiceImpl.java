package com.example.pruebas.services.implementations;

import com.example.pruebas.models.Vehiculo;
import com.example.pruebas.repositories.VehiculoRepository;
import com.example.pruebas.services.interfaces.VehiculoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServiceImpl extends ServiceImpl<Vehiculo, Integer> implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public void add(Vehiculo vehiculo) {
        this.vehiculoRepository.save(vehiculo);
    }

    @Override
    public void update(Vehiculo vehiculo) {
        this.vehiculoRepository.save(vehiculo);
    }

    @Override
    public void delete(Integer id) {
        Vehiculo vehiculo = this.vehiculoRepository.findById(id).orElseThrow();
        this.vehiculoRepository.delete(vehiculo);
    }

    @Override
    public Vehiculo findById(Integer id) {
        return this.vehiculoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Vehiculo> findAll() {
        return this.vehiculoRepository.findAll();
    }
}
