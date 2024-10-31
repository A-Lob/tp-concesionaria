package com.example.pruebas.services.implementations;

import com.example.pruebas.models.Interesado;
import com.example.pruebas.repositories.InteresadoRepository;
import com.example.pruebas.services.interfaces.InteresadoService;
import org.springframework.stereotype.Service;

@Service
public class InteresadoServiceImpl implements InteresadoService {

    private InteresadoRepository interesadoRepository;

    public InteresadoServiceImpl(InteresadoRepository interesadoRepository) {
        this.interesadoRepository = interesadoRepository;
    }


    @Override
    public void update(int id) {
        Interesado interesado = this.interesadoRepository.findById(id);
        this.interesadoRepository.save(interesado);
    }
}
