package com.example.pruebas.services.implementations;

import com.example.pruebas.models.Interesado;
import com.example.pruebas.repositories.InteresadoRepository;
import com.example.pruebas.services.interfaces.InteresadoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteresadoServiceImpl extends ServiceImpl<Interesado, Integer> implements InteresadoService {

    private final InteresadoRepository interesadoRepository;

    public InteresadoServiceImpl(InteresadoRepository interesadoRepository) {
        this.interesadoRepository = interesadoRepository;
    }

    @Override
    public void add(Interesado interesado) {
        this.interesadoRepository.save(interesado);
    }

    @Override
    public void update(Interesado interesado) {
        this.interesadoRepository.save(interesado);
    }

    @Override
    public void delete(Integer id) {
        Interesado interesado = this.interesadoRepository.findById(id).orElseThrow();
        this.interesadoRepository.delete(interesado);
    }


    @Override
    public Interesado findById(Integer id) {
        return this.interesadoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Interesado> findAll() {
        return this.interesadoRepository.findAll();
    }

}
