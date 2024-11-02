package com.example.pruebas.services.implementations;

import com.example.pruebas.models.Empleado;
import com.example.pruebas.repositories.EmpleadoRepository;
import com.example.pruebas.services.interfaces.EmpleadoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl extends ServiceImpl<Empleado, Integer> implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public void add(Empleado empleado) {
        this.empleadoRepository.save(empleado);
    }

    @Override
    public void update(Empleado empleado) {
        this.empleadoRepository.save(empleado);
    }

    @Override
    public void delete(Integer id) {
        Empleado empleado = this.empleadoRepository.findById(id).orElseThrow();
        this.empleadoRepository.delete(empleado);
    }

    @Override
    public Empleado findById(Integer id) {
        return this.empleadoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Empleado> findAll() {
        return this.empleadoRepository.findAll();
    }

}
