package com.example.pruebas.services.implementations;

import com.example.pruebas.models.Interesado;
import com.example.pruebas.models.Prueba;
import com.example.pruebas.models.Vehiculo;
import com.example.pruebas.repositories.InteresadoRepository;
import com.example.pruebas.repositories.PruebaRepository;
import com.example.pruebas.repositories.VehiculoRepository;
import com.example.pruebas.services.interfaces.PruebaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PruebaServiceImpl extends ServiceImpl<Prueba, Integer> implements PruebaService {

    private final PruebaRepository pruebaRepository;
    private final InteresadoRepository interesadoRepository;
    private final VehiculoRepository vehiculoRepository;

    public PruebaServiceImpl(PruebaRepository pruebaRepository, InteresadoRepository interesadoRepository, VehiculoRepository vehiculoRepository) {
        this.pruebaRepository = pruebaRepository;
        this.interesadoRepository = interesadoRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public void add(Prueba prueba) {
        this.pruebaRepository.save(prueba);
    }

    @Override
    public void update(Prueba prueba) {
        this.pruebaRepository.save(prueba);
    }

    @Override
    public Prueba delete(Integer id) {
        Prueba prueba = this.pruebaRepository.findById(id).orElseThrow();
        this.pruebaRepository.delete(prueba);
        return prueba;
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
    public Interesado findInteresadoById(int id) {
        return this.interesadoRepository.findById(id);
    }

    @Override
    public Vehiculo findVehiculoById(int id) {
        return this.vehiculoRepository.findById(id);
    }
}
